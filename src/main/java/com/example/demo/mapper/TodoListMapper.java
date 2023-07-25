package com.example.demo.mapper;

import com.example.demo.dto.TodoListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoListMapper {

    void insertData(TodoListDTO form);
    void deleteData(int todo_num);

    List<TodoListDTO> searchAll(String user_id);
    List<TodoListDTO> searchData(String user_id, String todo_search);

}
