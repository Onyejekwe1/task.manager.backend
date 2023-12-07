# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17-jdk-slim as build

# Add Maintainer Info
LABEL maintainer="ifeanyiwisdom25@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ARG JAR_FILE=target/task.manager-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
