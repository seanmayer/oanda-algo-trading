# Running on MiniKube

### Prerequisites:
- MiniKube installed on your machine
  - Brew: `brew install minikube`
- Docker installed on your machine
    - Brew: `brew install docker`
- kubectl installed on your machine (comes with MiniKube)

1. **Start MiniKube**

   ```bash
   minikube start
   ```
   
2. **Build the Docker Image**

    Navigate to the root directory of your project and run the following command to build the Docker image:
    
    ```bash
    eval $(minikube docker-env)
    docker build -t oanda-trading-api .
    ```
   
3. **Apply the Manifest**
    ```bash
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
    ```
5. **Access the Application**

   To access the application, you need to find the IP address of the Minikube cluster and the NodePort of the service.

   ```bash
   minikube ip
   kubectl get svc
   ```

   Open a web browser and navigate to `http://<minikube_ip>:30000`.
6. **Stop MiniKube**

   ```bash
   minikube stop
   ```