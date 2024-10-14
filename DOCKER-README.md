# Oanda Trading API

## How to Run the Project with Docker

### Prerequisites

- Docker installed on your machine

### Steps

1. **Build the Docker Image**

   Navigate to the root directory of your project and run the following command to build the Docker image:

   ```bash
   docker build -t oanda-trading-api .
   ```

2. **Run the Docker Container**

   After the image is built, run the following command to start a container from the image:

   ```bash
   docker run -p 8080:8080 -e OANDA_API_KEY=${OANDA_API_KEY} oanda-trading-api
   ```

   This will start the application and map port 8080 of the container to port 8080 on your host machine.

### Accessing the Application

Once the container is running, you can access the application by navigating to `http://localhost:8080` in your web browser.

### Stopping the Container

To stop the running container, you can use the `docker ps` command to find the container ID and then stop it using the `docker stop` command:

```bash
docker ps
docker stop <container_id>
```

Replace `<container_id>` with the actual ID of your running container.
```

This `README.md` file provides clear instructions on how to build and run the Docker container for your project.