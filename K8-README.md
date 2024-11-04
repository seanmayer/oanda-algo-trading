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

8. **Access the Application**
    - Get the Minikube IP and service NodePort:
      ```bash
      minikube ip
      kubectl get svc
      ```
    - Access the application at `http://<minikube-ip>:<NodePort>`.

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

This setup should simplify your workflow and ensure everything runs smoothly using Docker Hub for image management. Let me know if there are any more updates or issues you’d like to address!