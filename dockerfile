# Stage 1: Build the application
FROM --platform=$BUILDPLATFORM maven:3.9.6-eclipse-temurin-17 as builder

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Build the application (adjust as needed for your Spring Boot setup)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM --platform=$BUILDPLATFORM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Expose port (commonly 8080 for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["/wait-for-it.sh", "--", "java", "-jar", "app.jar"]