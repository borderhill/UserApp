# Linux with OpenJDK JRE
FROM openjdk:latest
# copy WAR into image
COPY spring-boot-0.1.0.war /app.war 
# run application with this command line 
#CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.war"]
CMD ["/usr/bin/java", "-jar", "/app.war"]
