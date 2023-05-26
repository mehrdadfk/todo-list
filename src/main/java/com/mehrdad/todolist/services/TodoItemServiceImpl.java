package com.mehrdad.todolist.services;

import com.mehrdad.todolist.models.TodoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    List<TodoItem> items;
    public TodoItemServiceImpl() {
        this.items = new ArrayList<>();
    }

    @Override
    public TodoItem createTodoItem(TodoItem item) {

        items.add(item);

        return item;
    }
}
