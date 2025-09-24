# Etapa de compilación
# Etapa de compilación
# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY applications/app-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
