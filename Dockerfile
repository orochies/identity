FROM openjdk:17-jdk-alpine

COPY build/libs/identity-api.jar identity-api.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "identity-api.jar"]