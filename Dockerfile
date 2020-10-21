FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
RUN apk add --no-cache maven
ADD . /src
WORKDIR /src
RUN mvn clean install
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/picture_cache-1.0-SNAPSHOT.jar"]