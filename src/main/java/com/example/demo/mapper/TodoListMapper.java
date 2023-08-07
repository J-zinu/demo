package com.example.demo.mapper;

import com.example.demo.dto.TodoListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TodoListMapper {

    int insertData(TodoListDTO form);
    boolean deleteData(int todo_num, String user_id);
    int updateData(int todo_num, String todo, String user_id);

    List<TodoListDTO> searchAll(String user_id);
    List<TodoListDTO> searchData(String user_id, String todo_search);

}



