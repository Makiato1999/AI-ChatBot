#!/bin/bash

# Pull the latest image (optional step, if you want to ensure the latest version is used)
docker pull chatbot-api:1.0

# Stop the previous container if it exists
docker stop ChatBot-api
docker rm ChatBot-api

# Run the new container
docker run -p 8090:8090 --name ChatBot-api -d chatbot-api:1.0