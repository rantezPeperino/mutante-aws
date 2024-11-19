FROM tomcat:10-jdk17

# Actualizar el sistema e instalar nano
RUN apt-get update && \
    apt-get install -y nano && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Eliminar la aplicación ROOT por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Crear directorio para la aplicación
WORKDIR /usr/local/tomcat/webapps

# Copiar el archivo WAR
COPY target/mutante.war mutante.war

# Puerto expuesto
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]