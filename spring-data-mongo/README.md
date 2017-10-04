NHL App

This is an example Spring Boot application that utilizes Spring Rest and Spring Data with MongoDB.

All data for NHL Teams and Players was obtained from https://fantasydata.com through their REST API.

Build

From the base project directory, invoke "mvn clean package"
Prerequisite for running the application

Ensure that MongoDB is running with defaults on the local machine
Import the Team and Player repositories into MongoDB 

Run the application

From the base project directory, invoke "java -jar target/boot-rest-data-1.0.0-SNAPSHOT.jar"

Navigate to http://localhost:8080/swagger-ui.html to use the generated user interface to interact with the REST endpoints.