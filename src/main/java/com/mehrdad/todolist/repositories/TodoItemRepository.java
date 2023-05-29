package com.mehrdad.todolist.repositories;

import com.mehrdad.todolist.model.TodoItemDTO;
import com.mehrdad.todolist.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    @Query("select t from TodoItem t where t.status = com.mehrdad.todolist.models.TodoItem$StatusEnum.NOT_DONE")
    List<TodoItem> findAllByStatusNotDone();
}
