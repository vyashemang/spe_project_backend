#Pulling java base image from dockerhub
FROM openjdk:11

#Adding jar file
ADD target/spe_project-0.0.1-SNAPSHOT.jar app.jar

#Exposing port number 8082
EXPOSE 8082

#Running the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
