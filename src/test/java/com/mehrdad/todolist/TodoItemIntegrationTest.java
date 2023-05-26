package com.mehrdad.todolist;

import com.mehrdad.todolist.controllers.TodosApiController;
import com.mehrdad.todolist.model.NewTodoItemDTO;
import com.mehrdad.todolist.model.TodoItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class TodoItemIntegrationTest {
    @Autowired
    TodosApiController todosApiController;

    @Test
    void testAddTodoItem() {
        NewTodoItemDTO newItem = new NewTodoItemDTO("New Todo Item", LocalDateTime.now());
        newItem.setDescription("New Todo Item");

        ResponseEntity<TodoItemDTO> response = todosApiController.createTodo(newItem);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

