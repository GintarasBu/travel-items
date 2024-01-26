# **Travel Items Demo Application**

## Overview
This repository contains the source code for the "Travel Items" demo application.  
The application is built using the Spring Boot framework.

## Getting Started
To run the application, use the following command:
``` batch
mvn spring-boot:run
```

The application will start, and you can access the "Swagger UI" at the following address:   
http://localhost:8080/swagger-ui/index.html   

## Retrieve Travel Items List

To retrieve a list of travel items required for a specific season and duration, you can make an HTTP GET request.    
For example:
``` batch
curl http://localhost:8080/api/travel-items/summer/10
```

Alternatively, you can use the "Swagger UI" interface to make the request interactively.

## Add Items to the Travel List
You can add items to the travel list by sending an HTTP POST request.  
For example:
``` batch
curl -X POST -H "Content-Type: application/json" -d '{
  "title": "Mosquito spray",
  "weight": 0.2,
  "quantity": 1,
  "distance": 15,
  "season": "SUMMER"
}' http://localhost:8080/api/travel-items
```

Feel free to customize the request body with the details of the travel item you want to add.  
Alternatively, you can use the "Swagger UI" interface to make the request interactively.
  
> **_NOTE:_**  The database only contains items suitable for summer.
  
Feel free to explore the application and customize it according to your needs.
