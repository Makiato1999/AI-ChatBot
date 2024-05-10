# ChatBot-api Project Overview
## Description
The ChatBot API project leverages the capabilities of ChatGPT to address common and generic questions encountered during the daily research and development learning processes. It is designed to streamline the handling of query information and facilitates the compilation of resources based on accumulated questions. This project is integrated with GitHub Issues to enhance issue tracking and resolution through automated interactions.

# Application Deployment Guide
## Prerequisites
Before proceeding, ensure that Docker is installed on your system. You can download and install Docker from Docker's official website.

## Pulling the Docker Image
First, pull the image from Docker Hub:
```
docker pull makiato1999/chatbot-api-repo:1.0
```
## Running the Container
To run the container in the background, use the following command:
```
docker run -d -p 8090:8090 --name ChatBot-api makiato1999/chatbot-api-repo:1.0
```
Options Explained
- -d: Run the container in detached mode (in the background).
- -p 8090:8090: Map port 8080 of the container to port 8090 on the host. Adjust the ports as necessary for your application.
- --name ChatBot-api: Name the container ChatBot-api for easy reference.

## Starting the application
Then you can go to the link
```
https://github.com/Makiato1999/ChatBot-api/issues
```
Post your question as a new issue, and the AI will respond within about 5 seconds.
