# Running on Minikube with Docker Hub

### Prerequisites:
- **Minikube** installed on your machine
    - Install with Brew:
      ```bash
      brew install minikube
      ```
- **Colima** installed on your machine (as a replacement for Docker Desktop)
    - Install with Brew:
      ```bash
      brew install colima
      ```
    - Start Colima to manage Docker:
      ```bash
      colima start
      ```
- **Docker** installed on your machine
    - Install with Brew:
      ```bash
      brew install docker
      ```
- **kubectl** installed on your machine (comes with Minikube)

---

### Instructions

1. **Start Colima**
   Make sure Colima is running to use Docker as the runtime:
   ```bash
   colima start
   ```

2. **Set Docker Context to Colima**
   Ensure Docker is using Colima as the active context:
   ```bash
   docker context use colima
   ```

3. **Start Minikube with Docker as the Driver**
   Start Minikube using Docker:
   ```bash
   minikube start --driver=docker
   ```
   > **Note**: The initial pull of the Minikube base image may take some time. Be patient if it appears to hang at this step.

4. **Build and Tag Your Docker Image**
   Build your Docker image and tag it for Docker Hub:
   ```bash
   docker build -t oanda-trading-api .
   docker tag oanda-trading-api:latest <your-dockerhub-username>/oanda-trading-api:latest
   ```

5. **Push the Image to Docker Hub**
   Log in to Docker Hub and push your image:
   ```bash
   docker login
   docker push <your-dockerhub-username>/oanda-trading-api:latest
   ```

6. **Update Kubernetes Manifest**
   Make sure your `deployment.yaml` is configured to pull the image from Docker Hub:
   ```yaml
   spec:
     containers:
     - name: oanda-trading-api
       image: <your-dockerhub-username>/oanda-trading-api:latest
       ports:
       - containerPort: 8080
   ```

7. **Deploy to Minikube**
   Apply your Kubernetes configuration:
   ```bash
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
   ```
8. **Secrets**
   ```bash
   kubectl create secret generic oanda-api-secrets --from-literal=OANDA_API_KEY="$OANDA_API_KEY"
    ```
9. **Access the Application**

   - Accessing and Testing Your Application on Minikube

    Once your application is deployed, you can access it using various methods. Here are the most effective approaches:

    Port Forwarding for Local Access
    Port forwarding provides a straightforward way to access your service on localhost without needing to know the Minikube IP or NodePort.

    ```bash
    kubectl port-forward svc/oanda-trading-api-service 8080:80
    ```

    Now, access the API at:
    http://localhost:8080/your-endpoint

	- Using the Minikube Service Command
        Minikube’s built-in service command simplifies access by automatically opening your service’s URL or providing the correct IP and port.
   
    ```bash
    minikube service oanda-trading-api-service
    ```

    This command attempts to open the service in your default browser. If it cannot open a browser, it will output the URL, which you can paste into a browser or Postman.

	- Testing Internal Connectivity with BusyBox
    If you need to verify that services are reachable inside the Minikube cluster, you can use a temporary BusyBox container to test connectivity.
	    - Start a BusyBox Pod:
        ```bash
          kubectl run -it --rm debug --image=busybox --restart=Never -- /bin/sh
        ```
	- Test Connectivity to Your Service:
       - Inside the BusyBox shell, you can use wget or curl to check if your service is reachable:
       ```bash
       - wget http://oanda-trading-api-service:80/your-endpoint
      ```

	- Minikube Tunnel for External Access
       - If you need to expose Minikube services to external networks or specific ports, consider setting up a Minikube tunnel.
         - Start the Tunnel:
         ```bash
         minikube tunnel
         ```

This will create routes to expose Kubernetes services on your local network. Keep this terminal open to maintain the tunnel, and note that it may require administrative privileges.

---

### Notes and Troubleshooting
- **Waiting for Image Pull**: If Minikube seems to hang while pulling the base image, wait patiently. The initial setup can take time, especially on a slow network.
  - **Docker Credential Issues**: If you encounter errors related to `docker-credential-osxkeychain`, make sure to remove or update the `"credsStore"` entry in `~/.docker/config.json`.
  - **Colima Resource Configuration**: If you run into performance issues, allocate more resources to Colima:
    ```bash
    colima stop
    colima start --cpu 4 --memory 4
    ```
  - **Manual Base Image Pull**: If Minikube's image pull fails, you can manually pull the image:
    ```bash
    docker pull gcr.io/k8s-minikube/kicbase:v0.0.45
    ```