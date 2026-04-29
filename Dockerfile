# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy Maven wrapper and pom first (better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission to mvnw (this fixes the 126 error)
RUN chmod +x ./mvnw

# Download dependencies (helps with caching)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]