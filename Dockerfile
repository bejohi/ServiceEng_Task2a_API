# Dockerfile for Vocable Project
#
# Build Backend API
FROM maven:3.6-openjdk-14-slim AS backend
COPY vocab/src /home/app/src
COPY vocab/pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml clean package

# Package Stage
FROM openjdk:14-slim
COPY --from=backend /home/app/target/*.jar /usr/local/lib/vocab.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/usr/local/lib/vocab.jar"]