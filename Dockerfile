FROM openjdk:8

COPY ./target/docker-service-1.0-SNAPSHOT.jar docker-service-1.0-SNAPSHOT.jar

EXPOSE 5000

ENTRYPOINT ["java","-jar","docker-service-1.0-SNAPSHOT.jar" ]

