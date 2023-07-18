# Todo List Application

This is a simple todo list application built using Spring Boot, Hibernate, and MySQL. The application provides a RESTful API for managing todo items, allowing users to create, read, update, and delete items. Each item consists of a title and a description.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or higher
- MySQL database

## Getting Started

Follow the instructions below to set up and run the application:

1. Clone the repository to your local machine:
```
git clone <repository-url>
```

2. Open the project in your favorite IDE.

3. Set up the MySQL database:
- Create a new database named `todo_db`.
- Update the database configuration in the `application.properties` file located in the `src/main/resources` directory. Modify the `spring.datasource.username` and `spring.datasource.password` properties with your MySQL database credentials.

4. Build the application using Maven:
```
cd todo-list-application
mvn clean package
```


5. Run the application:
```
java -jar target/todo-list-application.jar
```

6. The application will start running on `http://localhost:8080`.

## API Documentation

The application provides the following API endpoints:

- `GET /api/todos`: Get all todos.
- `GET /api/todos/{id}`: Get a todo by ID.
- `POST /api/todos`: Create a new todo.
- `PUT /api/todos/{id}`: Update a todo by ID.
- `DELETE /api/todos/{id}`: Delete a todo by ID.

Please refer to the [TodoController](src/main/java/com/example/todo/TodoController.java) class for detailed information on the API endpoints.

## Testing

The application includes unit tests to verify its functionality. You can run the tests using Maven:
```
mvn test
```


## Contributing

Contributions are welcome! If you find any issues or would like to suggest improvements, please create a new issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Feedback

**Was it easy to complete the task using AI?**

    Yes, that was quite easy. 

**How long did task take you to complete?**

    It took me more than 1 hour.

**Was the code ready to run after generation? What did you have to change to make it usable?**

    All the code was almost ready to run after generation. I changed some code on my own to make it usable.

**Which challenges did you face during completion of the task?**

    Lack of knowledge of the ChatGPT.

**Which specific prompts you learned as a good practice to complete the task?**

    For example, step-by-step, error handling, testing and documentation prompts. 