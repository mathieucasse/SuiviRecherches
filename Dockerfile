# docker build . -t suivi-recherche-service -f Dockerfile
# docker run --rm -d -p 8089:8080 suivi-recherche-service:latest
# docker exec -it 81e11c2e294f ls
ARG appJar='SuiviRecherches-0.1.0-SNAPSHOT.jar'

FROM openjdk:8-jdk-alpine
#RUN mkdir -p /app
#COPY target/SuiviRecherches-0.1.0-SNAPSHOT.jar /app
#WORKDIR /app
#RUN sh -c "touch $appJar"
ADD target/SuiviRecherches-0.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
EXPOSE 8080
