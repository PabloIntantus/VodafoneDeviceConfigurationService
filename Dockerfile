FROM openjdk:17-jdk-slim
ADD target/vodafone-device-conf-service.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]