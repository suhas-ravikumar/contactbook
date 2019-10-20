## Description

This is a Java Spring Boot application with the basic CRUD functionality for a Contact Book Database. This supports basic CRUD Operations along with the search of Contact operation using either the Contact Name or the Contact Email ID.

## Installation

The packaging is done using [Maven](https://maven.apache.org/).
After extracting the zip content into a local folder, please run the below command. This will download all the required dependencies and also run the Unit Tests.

```bash
mvn clean install
```
You can also import this in local eclipse instance. 

## Configuration
The application is written in `Java 8`.  
The application is tested with [MySQL](https://www.mysql.com/) Database, but any database can be configured.  
All the MySQL configurations are done in the `application.properties` file.  
The Schema Name is `contactbookdatabase`.  
Here is an example of the database configuration. 
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/contactbookdatabase
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
```
You're good to go once the database connection configurations are performed.   
A context path of `/contactbook` is specified.  

## Implemented features
The below list of features are implemented
- Basic CRUD Functionality and search functionality by email ID and Contact Name along with appropriate errors.
- API for adding individual contacts or contacts in bulk
- Pagination support in the search with default 10 results per page.
- Unit Tests mainly for the service with the mocked repository.
- Basic authentication for the app with two roles, **USER** (For all the Get Requests) and **ADMIN** (For all the Get and modification requests).  
**Note:** *For the USER role, the username and password are **user/password** and for the ADMIN role, the username and password are **admin/password***.    
- Swagger API Documentation available at `/contactbook/swagger-ui.html`

## Unimplemented features
- OAuth2 Token Based authentication: Tried to implement, but the same was conflicting with the Basic Authentication. Removed the configuration files for Resource Server and Authentication Server but have included the same outside the project folder. 
- Integration Test: Implemented one Integration test with the in-memory H2 Database, but couldn't get it working as the schemas that the h2 created were conflicting with the same from the actual code. This Test case and its supporting file is also placed outside the project folder
- Deployment in AWS or any cloud service: Didn't get a chance to do this. 

