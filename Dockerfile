# --- Stage 1: Build the application ---
FROM maven:3.9.6-amazoncorretto-17 as builder

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# --- Stage 2: Create the runtime image ---
# Use the correct tag for the Debian-based image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/tasksProject-0.0.1-SNAPSHOT.jar ./tasksProject-0.0.1-SNAPSHOT.jar
COPY atom-firebase-credentials.json ./atom-firebase-credentials.json

# Specify the command to run on container start
CMD ["java", "-jar", "tasksProject-0.0.1-SNAPSHOT.jar"]

# Expose the port the app runs in
EXPOSE 8080