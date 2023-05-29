package com.mehrdad.todolist.services;

import com.mehrdad.todolist.models.TodoItem;

import java.util.List;


/**
 *
 */
public interface TodoItemService {
    /**
     * @param item new item
     * @return saved item
     */
    TodoItem createTodoItem(TodoItem item);

    /**
     *
     * @param onlyNotDone
     * @return all the items in "no done" status if parameter is true otherwise all the items
     */
    List<TodoItem> getAll(Boolean onlyNotDone);

    /**
     * @param id identifier of the item
     * @return found item by id
     */
    TodoItem getTodoItemById(Long id);

    /**
     * @param id          identifier of the item
     * @param description new description to be saved
     * @return updated Item
     */
    TodoItem updateTodoDescription(Long id, String description);

    /**
     *
     * @param id identifier of the item
     * @return updated Item
     */
    TodoItem markTodoAsDone(Long id);

    /**
     *
     * @param id identifier of the item
     * @return updated Item
     */
    TodoItem markTodoAsNotDone(Long id);

    void handleTodoItemStatusPastDue();
}
