FROM gradle:8.7-jdk17 AS builder
WORKDIR /app

COPY gradle .
COPY gradle gradle/
COPY build.gradle .
COPY settings.gradle .

COPY src src/

RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]