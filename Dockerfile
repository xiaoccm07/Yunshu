FROM maven:3.6.3-jdk-8 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q dependency:go-offline || true
COPY src ./src
RUN mvn -q package -DskipTests

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /build/target/yunshu-admin-system.jar app.jar
RUN mkdir -p /app/static/upload /app/data/attach /app/export
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
