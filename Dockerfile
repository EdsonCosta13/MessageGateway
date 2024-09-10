# Usar uma imagem base com o Maven e o OpenJDK 21
FROM maven:3.9.2-eclipse-temurin-21 AS builder

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e outros arquivos de configuração primeiro
COPY pom.xml .

# Baixar as dependências Maven
RUN mvn dependency:go-offline -B

# Copiar todo o código-fonte da aplicação
COPY src ./src

# Compilar a aplicação e gerar o arquivo JAR
RUN mvn clean package -DskipTests

# Usar uma imagem mais leve para rodar a aplicação
FROM eclipse-temurin:21-jdk-slim

# Definir o diretório de trabalho no novo container
WORKDIR /app

# Copiar o JAR gerado para a nova imagem
COPY --from=builder /app/target/*.jar app.jar

# Expor a porta que a aplicação Spring Boot estará rodando
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
