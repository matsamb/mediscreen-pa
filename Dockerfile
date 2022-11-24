FROM openjdk:17-jdk-slim-buster
EXPOSE 9004

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} patient-0.0.1-snapshot.jar
ENTRYPOINT ["java","-jar","/patient-0.0.1-snapshot.jar"]