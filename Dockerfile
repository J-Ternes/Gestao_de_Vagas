FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install eclipse-24-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:24-jdk-jammy
EXPOSE 8080

COPY --from=build /target/Gestao_Vagas-0.0.1.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]