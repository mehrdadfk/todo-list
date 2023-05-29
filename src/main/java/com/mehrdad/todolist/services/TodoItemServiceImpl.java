package com.mehrdad.todolist.services;

import com.mehrdad.todolist.exceptions.EntityNotFoundException;
import com.mehrdad.todolist.models.TodoItem;
import com.mehrdad.todolist.repositories.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TodoItemServiceImpl implements TodoItemService {

    final TodoItemRepository todoItemRepository;

    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {

        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public TodoItem createTodoItem(TodoItem item) {
        item.setStatus(TodoItem.StatusEnum.NOT_DONE); //by default a new item should be in not done state
        if (item.getDueDateTime() != null && item.getDueDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("The dueDateTime cannot be in the past.");
        }
        item.setCreatedAt(LocalDateTime.now());
        return todoItemRepository.save(item);
    }

    @Override
    public List<TodoItem> getAll(Boolean onlyNotDone) {
        if (Boolean.TRUE.equals(onlyNotDone)) {
            return todoItemRepository.findAllByStatusNotDone();
        } else return todoItemRepository.findAll();
    }

    @Override
    public TodoItem getTodoItemById(Long id) {

        Optional<TodoItem> todoItemById = todoItemRepository.findById(id);

        return todoItemById.orElseThrow(() -> new EntityNotFoundException(TodoItem.class, "id", id.toString()));
    }

    @Override
    public TodoItem updateTodoDescription(Long id, String description) {
        Optional<TodoItem> todoItemById = todoItemRepository.findById(id);
        TodoItem todoItem = todoItemById.orElseThrow(() -> new EntityNotFoundException(TodoItem.class, "id", id.toString()));

        todoItem.setDescription(description);
        todoItemRepository.save(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem markTodoAsDone(Long id) {
        Optional<TodoItem> todoItemById = todoItemRepository.findById(id);
        TodoItem todoItem = todoItemById.orElseThrow(() -> new EntityNotFoundException(TodoItem.class, "id", id.toString()));

        if(todoItem.getStatus().equals(TodoItem.StatusEnum.PAST_DUE))
            throw new IllegalArgumentException("Cannot mark a 'past due' task as done.");
        else {
            todoItem.setStatus(TodoItem.StatusEnum.DONE);
            todoItemRepository.save(todoItem);
        }
        return todoItem;
    }

    @Override
    public TodoItem markTodoAsNotDone(Long id) {
        Optional<TodoItem> todoItemById = todoItemRepository.findById(id);
        TodoItem todoItem = todoItemById.orElseThrow(() -> new EntityNotFoundException(TodoItem.class, "id", id.toString()));
        if(todoItem.getStatus().equals(TodoItem.StatusEnum.PAST_DUE))
            throw new IllegalArgumentException("Cannot mark a 'past due' task as done.");
        else {
            todoItem.setStatus(TodoItem.StatusEnum.NOT_DONE);
            todoItemRepository.save(todoItem);
        }
        return todoItem;
    }

    @Override
    public void handleTodoItemStatusPastDue() {
        //ideally this scheduled task should be called in some limited event based domain. for example, we should load all the item just for
        // a particular member. possibly an event should  be sent for example when a user logs into the app and our event
        // contains a member id, and we could retrieve items for that member

        List<TodoItem> allByStatusNotDone = todoItemRepository.findAllByStatusNotDoneAndBeforeDateTime(LocalDateTime.now());
        for(TodoItem byStatusNotDone : allByStatusNotDone){
            byStatusNotDone.setStatus(TodoItem.StatusEnum.PAST_DUE);
        }
        todoItemRepository.saveAll(allByStatusNotDone);
    }

}
