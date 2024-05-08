# ChatBot-api Project Overview
## Description
The ChatBot API project leverages the capabilities of ChatGPT to address common and generic questions encountered during the daily research and development learning processes. It is designed to streamline the handling of query information and facilitates the compilation of resources based on accumulated questions. This project is integrated with GitHub Issues to enhance issue tracking and resolution through automated interactions.

## ChatBot API Deployment Guide
### Prerequisites
- Docker installed on your machine. [Install Docker](https://docs.docker.com/get-docker/)

### Repository Structure
- `Dockerfile`: Contains all the commands needed to build the image.
- `start.sh`: Script to run the Docker container.
- `build.sh`: Script to build the Docker image.

### Setup Instructions
1. Clone this repository to your local machine:
    ```angular2html
    git clone https://github.com/yourusername/ChatBot-api.git
    cd ChatBot-api
    ```
2. To build the Docker image with the tag chatbot-api:1.0, run the following command:
    ```
    /bin/bash build.sh
    ```
3. To start the Docker container, use:
    ```angular2html
    /bin/bash start.sh
    ```
4. Here are some common Docker commands you might find useful:
- Viewing running containers: `docker ps`
- Stopping a container: `docker stop [container_name]`
- Removing a container: `docker rm [container_name]`
- Listing all Docker images: `docker images`

