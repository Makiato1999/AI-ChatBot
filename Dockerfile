# Use a base image with Java, for example, the OpenJDK 11 slim version
FROM openjdk:11-jre-slim

# Optionally set a maintainer label to identify the creator of the built images
LABEL maintainer="xiexiaoran"

# Copy the JAR file from your local machine's target directory to the Docker image
COPY chatbot-api-interfaces/target/ChatBot-api.jar /app/ChatBot-api.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/ChatBot-api.jar"]