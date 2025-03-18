# Use an image that includes Maven and JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK 21 image for running the app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/websockets-0.0.1-SNAPSHOT.jar websockets.jar

# Expose the application's port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "websockets.jar"]
