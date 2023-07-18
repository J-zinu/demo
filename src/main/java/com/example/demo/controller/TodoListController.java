package com.example.demo.controller;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.mapper.TodoListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TodoListController {

    @Autowired
    TodoListMapper todoListMapper;

    @GetMapping("/todoList")
    public String TodoList(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        if(user_id == null){ return "redirect:/"; }
        else{ return "todoList"; }
    }

    @PostMapping("/todoList/create")
    public String TodoListCreate(TodoListDTO form){
        todoListMapper.insertTodoList(form);
        return "redirect:/todoList";
    }

    @GetMapping("/todoList/get")
    @ResponseBody
    public List<TodoListDTO> TodoListGet(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        return todoListMapper.getTodoList(user_id);
    }

    @GetMapping("/todoList/delete")
    public String TodoListDelete(@RequestParam("todo_num") int todo_num){
        todoListMapper.deleteTodoList(todo_num);
        return "redirect:/todoList";
    }

//    @GetMapping("/todoList_detail")
//    public String TodoListDetail(){
//        return "todoList_detail";
//    }
}
