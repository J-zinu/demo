package com.example.demo.service;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.mapper.TodoListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoListService {

    TodoListMapper todoListMapper;
    public TodoListService(TodoListMapper todoListMapper) {
        this.todoListMapper = todoListMapper;
    }

    // 모두보기
    @Transactional
    public List<TodoListDTO> findAllList(String user_id){
        List<TodoListDTO> findAllList = todoListMapper.searchAll(user_id);
        return findAllList;
    }

    // 검색
    @Transactional
    public List<TodoListDTO> searchTodoList(String user_id, String todo_search){
        todoListMapper.searchData(user_id, todo_search);
        List<TodoListDTO> findDataList = todoListMapper.searchData(user_id, todo_search);
        return findDataList;
    }

    // 생성
    @Transactional
    public int createTodoList(TodoListDTO form, String user_id){
        if(todoListMapper.insertData(form) == 1){
            return 1;
        }
        else return -1;
    }

    // 수정
    @Transactional
    public int updateTodoList(int todo_num, String todo, String user_id){
        if(todoListMapper.updateData(todo_num, todo) == 1){
            return 1;
        }
        else return -1;
    }

    // 삭제
    @Transactional
    public int deleteTodoList(int todo_num, String user_id){
        if(todoListMapper.deleteData(todo_num) == 1){
            return 1;
        }
        else return -1;
    }
}
