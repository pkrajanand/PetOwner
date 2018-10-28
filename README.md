# PetOwner

1. An in-memory H2 database with two tables PET & OWNER. Setup data consists of 3 owner records and 4 pet records that are owned by different owners.

2. A backend application that exposes three HTTP JSON REST APIs  
	1. GET /owners (list all owners (first name, last name, city, pet_id))
	2. GET /pets (list all pets (name, birthday, owner_id))
	3. POST /pet (create new pet record)
3. A frontend that connects to backend server mentioned above to list owners, pets and create new pets.


## Development Environment

### System Requirements

> JDK 1.8

> Maven 3.5+

> npm 6.4+

### Dev Stack

> Spring Boot 2.0.6

> JDK 1.8

> ReactJs

> H2 in-memory DB

### Backend 

Run `mvn install` to get everything downloaded and make sure you're good on that front.

Run `mvn test` to run the back-end tests

Run `mvn spring-boot:run` to start the back end (spring-boot based) server, so that api endpoints are available at `http://localhost:8080/owners`

### Frontend

```
cd frontend
npm test
npm start
```
Access the app at `http://localhost:3000/` and use "Add New & Refresh Page" button to add more pets for the owners.

## Database Console

If you want to query the database, you can access [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
You can find the necessary connection details in src/main/resources/application.yml
 
## Open Issues

https://github.com/pkrajanand/PetOwner/issues 
