package com.mehrdad.todolist.controllers;

import com.mehrdad.todolist.api.ApiUtil;
import com.mehrdad.todolist.api.TodosApi;
import com.mehrdad.todolist.exceptions.EntityNotFoundException;
import com.mehrdad.todolist.model.NewTodoItemDTO;
import com.mehrdad.todolist.model.TodoItemDTO;
import com.mehrdad.todolist.model.UpdateTodoItemDTO;
import com.mehrdad.todolist.models.TodoItem;
import com.mehrdad.todolist.services.TodoItemService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mehrdad.todolist.mappers.TodoItemMapper.MAPPER_INSTANCE;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-22T19:07:41.530782+02:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.todoService.base-path:}")
public class TodosApiController implements TodosApi {

    @Autowired
    TodoItemService todoItemService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<TodoItemDTO> createTodo(@Valid @RequestBody NewTodoItemDTO newTodoItem
    ) {
        TodoItem newItem = MAPPER_INSTANCE.mapDtoToEntity(newTodoItem);
        TodoItem persisted = todoItemService.createTodoItem(newItem);
        TodoItemDTO persistedDto = MAPPER_INSTANCE.mapEntityToDto(persisted);
        ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.created(URI.create("/" + persisted.getId())).body(persistedDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<TodoItemDTO>> getAll(@Valid @RequestParam(value = "only_not_done", defaultValue = "true") Boolean onlyNotDone) {
        List<TodoItemDTO> all = todoItemService.getAll(onlyNotDone)
                .stream()
                .map(MAPPER_INSTANCE::mapEntityToDto).toList();
        return ResponseEntity.ok(all);

    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<TodoItemDTO> getTodoById(@PathVariable("id") String id) {

        TodoItem todoItemById = todoItemService.getTodoItemById(Long.valueOf(id));
        TodoItemDTO todoItemDTO = MAPPER_INSTANCE.mapEntityToDto(todoItemById);

        return ResponseEntity.ok(todoItemDTO);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<TodoItemDTO> markTodoAsDone(@PathVariable("id") String id) {

        TodoItem todoItem = todoItemService.markTodoAsDone(Long.valueOf(id));
        TodoItemDTO todoItemDTO = MAPPER_INSTANCE.mapEntityToDto(todoItem);
        return ResponseEntity.ok(todoItemDTO);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<TodoItemDTO> markTodoAsNotDone(@PathVariable("id") String id) {

        TodoItem todoItem = todoItemService.markTodoAsNotDone(Long.valueOf(id));
        TodoItemDTO todoItemDTO = MAPPER_INSTANCE.mapEntityToDto(todoItem);
        return ResponseEntity.ok(todoItemDTO);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<TodoItemDTO> updateTodoDescription(@PathVariable("id") String id, @Valid @RequestBody UpdateTodoItemDTO updateTodoItemDTO) {

        TodoItem todoItem = todoItemService.updateTodoDescription(Long.valueOf(id), updateTodoItemDTO.getDescription() );
        TodoItemDTO todoItemDTO = MAPPER_INSTANCE.mapEntityToDto(todoItem);
        return ResponseEntity.ok(todoItemDTO);

    }
}
