# Use uma imagem base do Java 21
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR gerado pela aplicação para o contêiner
COPY target/api-0.0.1-SNAPSHOT.jar app.jar




# Expõe a porta onde a aplicação Spring Boot estará rodando
EXPOSE 8080

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
