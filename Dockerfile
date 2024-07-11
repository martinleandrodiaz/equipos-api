FROM openjdk:17-jdk as builder

WORKDIR /app

COPY target/equipos-api-0.0.1-SNAPSHOT.jar /app/equipos-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "equipos-api.jar"]
