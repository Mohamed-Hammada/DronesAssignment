FROM gradle:7.2.0-jdk17
FROM openjdk:17
COPY build/libs/*.jar drones-app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/drones-app.jar"]