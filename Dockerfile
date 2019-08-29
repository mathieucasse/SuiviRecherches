# docker build . -t suivi-recherche-service -f Dockerfile
# docker run --rm -d -p 8089:8080 suivi-recherche-service:latest
# docker exec -it 81e11c2e294f ls

FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
COPY target/SuiviRecherches-0.0.3-SNAPSHOT.jar /app
WORKDIR /app
RUN sh -c 'touch SuiviRecherches-0.0.3-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","SuiviRecherches-0.0.3-SNAPSHOT.jar"]
EXPOSE 8080
