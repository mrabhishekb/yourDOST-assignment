# Use OpenJDK 17 (Spring Boot 3.x works well)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Copy all source code
COPY src ./src

# ✅ Copy todos.json into the app directory
COPY todos.json ./todos.json

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the app (skip tests to speed up)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (default Spring Boot)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/todoapp-0.0.1-SNAPSHOT.jar"]
