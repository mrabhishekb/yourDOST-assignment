# Todo App Assignment

## Overview
This is a simple **Todo application** built using **Spring Boot**. It allows users to:

- Add todos (single or multiple at once)  
- View all todos  
- Update a todo  
- Delete a todo  

Each todo has the following properties:

- `id` (auto-generated)  
- `title` (required)  
- `dueDate` (defaults to the next day if not provided)  
- `completed` (defaults to `false`)  

## Technologies Used
- Java 17+  
- Spring Boot 3.5.7  
- Maven  
- Docker (optional, for deployment)  

## Setup & Running Locally

### Prerequisites
- **Java JDK 17+** installed and `JAVA_HOME` set.  
- **Maven** is optional; the project uses the **Maven Wrapper (`./mvnw`)**, so no global Maven or Spring Boot installation is required.  

### Steps
1. Clone the repository:
  Commands:
  git clone https://github.com/mrabhishekb/yourDOST-assignment.git
  cd backend

2. Build Project:
   command:
  ./mvnw clean package

3. Run the application:
   command:
   java -jar target/todoapp-0.0.1-SNAPSHOT.jar

4. Access the local host at url
   http://localhost:8080/todos/

This is the deployed link: https://todoapp-3-r1vg.onrender.com/todos/ 
The application might take a short while to load at first, as the server initializes in the background.

Testing Instructions (Postman): 
GET all todos:
1. Open Postman
2. Set method to GET
3. URL: http://localhost:8080/todos/ or the deployed link(https://todoapp-3-r1vg.onrender.com/todos/)
4. Click Send ? you should see the list of todos.

POST a single todo:
1. Method: POST
2. URL: http://localhost:8080/todos/ or the deployed link
3. Body ? raw ? JSON
Ex: {
  "title": "Buy groceries"
}
4. Click Send ? the new todo should be returned with an ID.
PUT (update) a todo
1. Method: PUT
2. URL: http://localhost:8080/todos/id (ex: http://localhost:8080/todos/1 )or deployed link
3. Body ? raw ? JSON
Ex:
{
  "title": "Buy fruits",
  "completed": true
}
5. Click Send ? updated todo will be returned.
DELETE a todo
* Method: DELETE
* URL:  http://localhost:8080/todos/id
* Click Send ? should return status 204 (No Content) if successful.


