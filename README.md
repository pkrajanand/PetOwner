# PetOwner

A small application which achieves the following:

1. Create a database with two tables PET & OWNER with at least 3 records each (owners linked to pets) using a DB of your choice
2. Create a java application which exposes three HTTP JSON REST APIs (feel free to choose any Java framework you are familiar with):   
	1. GET /owners (list all owners (first name, last name, city, pet_id))
	2. GET /pets (list all pets (name, birthday, owner_id))
	3. POST /pet (create new pet record)
3. Create a React (webpack) SPA (feel free to use a boilerplate of your choice) which interfaces with the Java based API to list owners, pets and create new pets.


## Development Environment

### Backend 

Run `mvn install` to get everything downloaded and make sure you're good on that front.

Run `mvn test` to run the back-end tests

Run `mvn spring-boot:run` to start the back end (spring-boot based) server, so that api endpoints are available at `http://localhost:8080`


## Database Console

If you want to query the database, you can access [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
You can find the necessary connection details in src/main/resources/application.yml
 
