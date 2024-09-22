# Usar una imagen base con JDK 17 (puedes cambiar la versión si usas otra)
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Gradle en el contenedor
# Cambia 'build/libs/tu-aplicacion.jar' por la ruta correcta si es diferente
COPY /out/artifacts/Prueba_jar/Prueba.jar /app/inventario.jar

# Exponer el puerto que usa tu aplicación (puedes cambiarlo si es necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/inventario.jar"]
