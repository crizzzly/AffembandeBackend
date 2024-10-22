# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your build files (like jar) to the working directory -> not good practice
#COPY build/libs/AffenbandeBackend-0.0.1-SNAPSHOT.jar .

# Copy your application.yml file to the correct location in the container
#COPY src/main/resources/application.yml /app/config/application.yml

# Alternatively, copy to a standard Spring Boot location (root of the container or /app folder)
COPY src/main/resources/application.yml /app/application.yml

# Copy your application's build files and directories
COPY . .


# For example, if using Gradle
# or any specific build command you have
RUN ./gradlew build

# Expose the port that the Kotlin backend will run on
EXPOSE 8080

# Run the Kotlin app
CMD ["java", "-jar", "/build/libs/AffenbandeBackend-0.0.1-SNAPSHOT.jar"]
