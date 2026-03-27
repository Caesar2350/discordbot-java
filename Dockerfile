# Build stage
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage  
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# FIX: Copy SHADDED JAR (has Main-Class manifest)
COPY --from=builder /app/target/*-shaded.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
