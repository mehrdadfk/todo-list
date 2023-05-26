package com.mehrdad.todolist.controllers;

import com.mehrdad.todolist.api.TodosApi;
import com.mehrdad.todolist.model.NewTodoItemDTO;
import com.mehrdad.todolist.model.TodoItemDTO;
import com.mehrdad.todolist.models.TodoItem;
import com.mehrdad.todolist.services.TodoItemService;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.net.URI;
import java.util.Optional;
import io.swagger.v3.oas.annotations.*;

import static com.mehrdad.todolist.mappers.TodoItemMapper.MAPPER_INSTANCE;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-22T19:07:41.530782+02:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.todoService.base-path:}")
public class TodosApiController implements TodosApi {

    @Autowired
    TodoItemService todoItemService;

    private final NativeWebRequest request;

    @Autowired
    public TodosApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    @Override
    public ResponseEntity<TodoItemDTO> createTodo(
            @Parameter(name = "NewTodoItemDTO", description = "", required = true) @Valid @RequestBody NewTodoItemDTO newTodoItem
    ){
        TodoItem newItem = MAPPER_INSTANCE.mapDtoToEntity(newTodoItem);
        TodoItem persisted = todoItemService.createTodoItem(newItem);
        TodoItemDTO persistedDto = MAPPER_INSTANCE.mapEntityToDto(persisted);
        ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.created(URI.create("/" + persisted.getId())).body(persistedDto);
    }



}
