# Estágio de Build
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia apenas o pom.xml primeiro para aproveitar o cache das dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o build
COPY src ./src
RUN mvn clean install -DskipTests

# Estágio Final (Execução)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
EXPOSE 8080

# CORREÇÃO AQUI: O caminho completo com o -SNAPSHOT
COPY --from=build /app/target/Gestao_Vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
