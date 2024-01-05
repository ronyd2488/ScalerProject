FROM openjdk:17-alpine
EXPOSE 8080
ADD target/scalerproj-automation.jar scalerproj-automation.jar
ENTRYPOINT ["java", "-jar", "/scalerproj-automation.jar"]
