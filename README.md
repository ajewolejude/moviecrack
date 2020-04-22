# moviecrack

## Functional Requirements
Create a web application with the following requirements:
1. Users should be able to register accounts before using the platform with basic
information(first name, surname, username and password).
2. The home screen should display a paginated list of movies (10 per page) with navigation
to other pages if there are more items to be displayed.
3. Selecting a movie should display details of the movie.
a. The user should also be able to watch embedded video trailers of the movie.
Videos are YouTube videos.

4. An authenticated user should be able to add a movie to their list of favorite movies.
5. An authenticated user should be able to manage the favorite movies (remove from
favorites, add to favorites, etc).


## Architecture:
-Contollers (Including REST)
-Exceptions
-Services
-Repositories
-Models
-Security
-Configuration
-Validator




# Configuration 

## Prerequisites
-Java 8
-Spring Boot 2.2.6

## Dependencies
Spring Security
Spring Boot
Spring Data JPA
Maven
Thymeleaf
Hibernate (Embeded Local File Storage)
JQuery
Ajax
Spring Security
JWT


## Database
Hibernate -  check application.properties file for details
Flyway for database migrations

## Run
```
mvn clean spring-boot:run
```

## Alternative Run
```
run WootlabprojectApplication in package com.greychain.wootlabproject;
```




# Assumptions
1. User role is unneccessary 
2. Password Encryption Mode.



# Feedback
1. The movie list has a field vidoes, instead of videos





