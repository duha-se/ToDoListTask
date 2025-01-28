# CodeClimb Task Manager - Backend (Spring Boot)

## Overview

This is the backend for a Todo list software built with Spring Boot. It provides a set of RESTful APIs to manage
tasks, including creating, updating, deleting, and retrieving tasks.

## Features

- Create, update, display and delete tasks.
- Filter tasks by status.
- Retrieve all tasks where the dueDate has passed and the status is still pending.


## Technologies

- Spring Boot
- JPA (Java Persistence API)
- MySQL
- Maven
## MySql dump file:
How to Import a MySQL Dump File:
1. Open the  terminal and log in to your MySQL server:
mysql -u <username> -p
Replace <username> with your MySQL username , and enter your password when prompted.
2. CREATE DATABASE <database_name>;
3. mysql -u <username> -p <database_name> < /path/to/dump_todo.sql

## Postman Documentation:
Import the Collection

1. In Postman, go to the "File" > "Import" menu or click the Import button.
2. Select the postman_collection.json file and import it.
3. Set the Base URL: Update the base URL in the Postman environment :
   http://localhost:8080/api

Once imported, you’ll see all the API endpoints (e.g., /tasks, /tasks/{id}).
Select the desired endpoint, set the necessary parameters or body, and click Send to test the API.
## Available Endpoints
Here are the key endpoints available in the API:

| Method        | Endpoint     | Description|
|---------------| -------- | ---------------| 
| `POST`        | `/tasks` | Create a new task|
| `GET`       | `/tasks` | View all tasks.|
| `GET` | `/tasks/{id}` | Get details of a specific task by ID.|
| `PUT`      | `/tasks/{id}	` | Update an existing task. |
| `PATCH`    | `/tasks/{id}/status` | Update the status of a task.|
| `DELETE`     | `/tasks/{id}`   | Delete a task by ID. |
| `GET`     | `/tasks/filter?status={}`   |Filter tasks by status. |
| `GET`     | `/tasks/overdue`   | Get a list of overdue tasks. |

