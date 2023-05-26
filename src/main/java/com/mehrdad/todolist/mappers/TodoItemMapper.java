package com.mehrdad.todolist.mappers;

import com.mehrdad.todolist.model.NewTodoItemDTO;
import com.mehrdad.todolist.model.TodoItemDTO;
import com.mehrdad.todolist.models.TodoItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface TodoItemMapper {
    TodoItemMapper MAPPER_INSTANCE = Mappers.getMapper(TodoItemMapper.class);

    @Mappings({
            // no specific mappings
    })
    TodoItemDTO mapEntityToDto(TodoItem item);

    @InheritInverseConfiguration
    TodoItem mapDtoToEntity(NewTodoItemDTO item);

}
