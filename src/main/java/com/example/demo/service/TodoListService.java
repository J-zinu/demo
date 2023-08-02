package com.example.demo.service;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.mapper.TodoListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TodoListService {

    TodoListMapper todoListMapper;

    public TodoListService(TodoListMapper todoListMapper) {
        this.todoListMapper = todoListMapper;
    }

    // 모두보기
    public List<TodoListDTO> findAllList(String user_id){
        List<TodoListDTO> findAllList = todoListMapper.searchAll(user_id);
        return findAllList;
    }

    // 검색
    public Map<String, Object> searchTodoList(String user_id, String todo_search){
        Map<String, Object> searchMap = new HashMap<>();
        List<TodoListDTO> searchData = todoListMapper.searchData(user_id, todo_search);
        searchMap.put("data", searchData);
        searchMap.put("serviceBool", true);

        return searchMap;
    }

    // 생성
    public int createTodoList(TodoListDTO form){
        if(todoListMapper.insertData(form) == 1){
            return 1;
        }
        else return -1;
    }

    // 수정
    public int updateTodoList(int todo_num, String todo, String user_id){
        if(todoListMapper.updateData(todo_num, todo, user_id) == 1){
            return 1;
        }
        else return -1;
    }

    // 삭제
    public boolean deleteTodoList(int todo_num, String user_id){
        if(todoListMapper.deleteData(todo_num, user_id)){
            return true;
        }
        else return false;
    }

}
