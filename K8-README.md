Here’s the README with all `bash` commands properly formatted.

---

# Running on MiniKube

### Prerequisites:
- **MiniKube** installed on your machine
    - Install with Brew:
      ```bash
      brew install minikube
      ```
- **Colima** installed on your machine
    - Install with Brew:
      ```bash
      brew install colima
      ```
    - Colima is an optimized container runtime for macOS, ideal for Kubernetes development. It acts as a replacement for Docker Desktop.
    - Use `colima start` to initiate the container runtime.
- **Docker** installed on your machine
    - Install with Brew:
      ```bash
      brew install docker
      ```
- **kubectl** installed on your machine (comes with MiniKube)

---

### Instructions

1. **Start Colima**

   ```bash
   colima start
   ```

2. **Start MiniKube with Docker as the Driver**

   ```bash
   minikube start --driver=docker
   ```

3. **Build the Docker Image**

   Navigate to the root directory of your project and run the following commands to build and tag the Docker image:

    ```bash
    eval $(minikube docker-env)
    docker build -t oanda-trading-api .
    ```

4. **Enable and Configure Registry Addon in MiniKube**

   Enable the Minikube registry addon to create an internal Docker registry:

    ```bash
    minikube addons enable registry
    ```

   **Note**: By default, the registry is accessible within the Minikube cluster as a `ClusterIP` service. To make it accessible from outside the cluster, you can convert it to a `NodePort` service.

5. **Expose the Registry as a NodePort**

   Changing the registry service to `NodePort` makes it accessible to your local machine on an external port, allowing Docker images to be pushed directly to Minikube’s registry.

    - Set **nano** as your default editor (temporarily for this session):

      ```bash
      export KUBE_EDITOR="code"
      ```

    - Open the registry service in `code`:

      ```bash
      kubectl edit svc registry -n kube-system
      ```

    - In `nano`, change the service type from `ClusterIP` to `NodePort`:

      ```yaml
      spec:
        type: NodePort
        ports:
          - port: 80
            targetPort: 80
      ```

    - Save your changes (`CTRL + O`, then `Enter`) and exit (`CTRL + X`).

6. **Get the Minikube Registry IP and NodePort**

   Use the following command to retrieve the Minikube IP address and the assigned `NodePort` for the registry:

    ```bash
    kubectl get svc registry -n kube-system
    ```

    - **Good Practice**: Note that exposing internal services via `NodePort` is common in development but can pose security risks if used in production without proper controls. This should only be done in isolated development environments.

7. **Tag and Push the Docker Image to the Minikube Registry**

   Tag and push the Docker image using the Minikube IP and `NodePort` retrieved from the previous step. For example:

    ```bash
    docker tag oanda-trading-api:latest <minikube_ip>:<node_port>/oanda-trading-api:latest
    docker push <minikube_ip>:<node_port>/oanda-trading-api:latest
    ```

8. **Deploy the Application**

   Apply the Kubernetes manifest files to create the deployment and service:

    ```bash
    kubectl apply -f deployment.yaml
    kubectl apply -f service.yaml
    ```

9. **Access the Application**

   Retrieve the Minikube IP address and the NodePort for your service to access the application:

   ```bash
   minikube ip
   kubectl get svc
   ```

   Open a web browser and navigate to `http://<minikube_ip>:<NodePort>` (e.g., `http://192.168.49.2:30000`).

10. **Stop MiniKube**

    When you're done, stop the Minikube cluster with:

    ```bash
    minikube stop
    ```

---

### Best Practices and Considerations

- **Security**: Exposing internal services like the Docker registry via `NodePort` is suitable for local development but is not recommended in production environments without additional security. Always secure the registry or limit access if you move this setup beyond development.

- **Persistence**: Be aware that Minikube environments are generally ephemeral. If you restart Minikube, you may need to reconfigure the registry `NodePort`.

- **Performance**: When running Kubernetes locally with Colima and Minikube, resource allocation (CPU, memory) can impact performance. Adjust these based on your system capabilities.

This setup allows you to efficiently build, push, and deploy Docker images to a local Kubernetes cluster, keeping the registry accessible without needing complex network configurations.