FROM openjdk:17-jdk-slim-buster
EXPOSE 9004

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} patient.jar
ENTRYPOINT ["java","-jar","/patient.jar"]