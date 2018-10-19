# 1819_5ahif_nvs_microprofile_microproject
Erste Microprofile-Programm für Thorntail

<https://rieckpil.de/howto-bootstrap-a-java-ee-8-and-microprofile-2-0-maven-project-in-seconds/>


Build:
mvn package

Run:
start derby db
---
driver-name: derby
        connection-url: jdbc:derby://localhost:1527/DbDS;create=true
        user-name: APP
        password: APP
---
start application
---
cd target
java -jar bakeme-thorntail.jar 
