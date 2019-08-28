# docker build . -t suivi-recherche-service -f Dockerfile
# docker run --rm -d -p 8089:8080 suivi-recherche-service:latest
# 

FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
COPY target/SuiviRecherches-0.0.3-SNAPSHOT.jar /app
WORKDIR /app
RUN sh -c 'touch SuiviRecherches-0.0.3-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","SuiviRecherches-0.0.3-SNAPSHOT.jar"]
EXPOSE 8080


#ENTRYPOINT ["/usr/bin/java"]
#CMD ["-jar", "/opt/spring-cloud/lib/spring-cloud-config-server.jar"]

#VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","ch.matfly.suivirecherches.SuiviRecherchesApplication"]