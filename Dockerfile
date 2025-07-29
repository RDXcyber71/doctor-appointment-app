# Use a base image with Maven and Java 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Grant execute permission to the mvnw script
RUN chmod +x mvnw

# Build the application using the Maven wrapper
RUN ./mvnw clean package -DskipTests

# --- Second Stage: Create the final, smaller image ---
# Use a slim Java 17 image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/doctor-appointment-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]