package com.example.juniortask1;

import com.example.juniortask1.controller.TodoController;
import com.example.juniortask1.entity.Todo;
import com.example.juniortask1.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTests {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoController todoController;

    @Test
    public void testGetAllTodos() {
        List<Todo> todoList = new ArrayList<>();
        todoList.add(new Todo(1L, "Todo 1", "Description 1"));
        todoList.add(new Todo(2L, "Todo 2", "Description 2"));

        when(todoRepository.findAll()).thenReturn(todoList);

        List<Todo> result = todoController.getAllTodos();

        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo(1L, "Todo 1", "Description 1");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        ResponseEntity<Todo> response = todoController.getTodoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTodoById_NotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Todo> response = todoController.getTodoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        todo.setTitle("New Todo");
        todo.setDescription("New Description");

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoController.createTodo(todo);

        assertEquals("New Todo", result.getTitle());
        assertEquals("New Description", result.getDescription());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testUpdateTodo() {
        Todo existingTodo = new Todo(1L, "Existing Todo", "Existing Description");
        Todo updatedTodo = new Todo(1L, "Updated Todo", "Updated Description");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(existingTodo)).thenReturn(updatedTodo);

        ResponseEntity<Todo> response = todoController.updateTodo(1L, updatedTodo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTodo, response.getBody());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).save(existingTodo);
    }

    @Test
    public void testUpdateTodo_NotFound() {
        Todo updatedTodo = new Todo(1L, "Updated Todo", "Updated Description");

        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Todo> response = todoController.updateTodo(1L, updatedTodo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, never()).save(any(Todo.class));
    }

    @Test
    public void testDeleteTodoById() {
        Todo todo = new Todo(1L, "Todo", "Description");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        ResponseEntity<Void> response = todoController.deleteTodoById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    public void testDeleteTodoById_NotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = todoController.deleteTodoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, never()).delete(any(Todo.class));
    }
}

