package com.example.demo.mapper;

import com.example.demo.dto.TodoListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoListMapper {
    List<TodoListDTO> getTodoList();
    void insertTodoList(TodoListDTO todoListDTO);
    void deleteTodoList(int todo_num);
}
