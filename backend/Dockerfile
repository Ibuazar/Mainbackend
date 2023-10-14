# Use the official OpenJDK base image with Java 17
FROM openjdk:17

# Set the working directory in the container
WORKDIR /backend

# Copy the Maven project's POM and build files
COPY pom.xml .
COPY src ./src


#ADD  target/spring-backend-docker.jar /backend/spring-backend-docker.jar

# Copy the compiled JAR file from the target directory
COPY target/spring-backend-docker.jar /backend/spring-backend-docker.jar

# Expose the application's port (adjust as needed)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "spring-backend-docker.jar"]
