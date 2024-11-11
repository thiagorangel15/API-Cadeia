# Etapa 1: Construir a aplicação usando Maven 4.0.0 (caso tenha uma imagem específica)
FROM maven:4.0.0-eclipse-temurin-21 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o pom.xml e baixar as dependências (para aproveitar o cache do Docker)
COPY pom.xml .
RUN mvn clean install -DskipTests

# Copiar o código fonte do projeto
COPY src /app/src

# Construir o aplicativo
RUN mvn package -DskipTests

# Etapa 2: Criar a imagem para rodar a aplicação com Java
FROM eclipse-temurin:21-jre AS runtime

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR da etapa de construção
COPY --from=build /app/target/API-Cadeia-0.0.1-SNAPSHOT.jar /app/API-Cadeia.jar

# Expor a porta que o Spring Boot vai rodar (por padrão, 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/API-Cadeia.jar"]
