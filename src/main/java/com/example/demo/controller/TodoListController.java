package com.example.demo.controller;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.mapper.TodoListMapper;
import com.example.demo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TodoListController {

    @Autowired
    TodoListMapper todoListMapper;

    @Autowired
    TodoListService todoListService;

    @GetMapping("/todoList")
    public String TodoList(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        if(user_id == null){ return "redirect:/"; }
        else{ return "todoList"; }
    }

    @PostMapping("/todoList/create")
    @ResponseBody
    public List<TodoListDTO> TodoListCreate(TodoListDTO form, HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        List<TodoListDTO> createData = todoListService.createTodoList(form, user_id);
        return createData;
    }

    @GetMapping("/todoList/read")
    @ResponseBody
    public List<TodoListDTO> TodoListGet(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        List<TodoListDTO> getData = todoListService.findAllList(user_id);
        return getData;
    }

    @DeleteMapping("/todoList/delete")
    @ResponseBody
    public List<TodoListDTO> TodoListDelete(HttpSession session, int todo_num){
        String user_id = (String) session.getAttribute("user_id");
        List<TodoListDTO> deleteData = todoListService.deleteTodoList(todo_num, user_id);
        return deleteData;
    }

    @PostMapping("/todoList/search")
    @ResponseBody
    public List<TodoListDTO> TodoListSearch(HttpSession session, String todo_search) {
        System.out.println("todo_search = " + todo_search);
        String user_id = (String) session.getAttribute("user_id");
        List<TodoListDTO> searchData = todoListService.searchTodoList(user_id, todo_search);
        return searchData;
    }



}
//    @GetMapping("/todoList")
//    public String TodoList(HttpSession session){
//        String user_id = (String) session.getAttribute("user_id");
//        if(user_id == null){ return "redirect:/"; }
//        else{ return "todoList"; }
//    }
//
//    @PostMapping("/todoList/create")
//    public String TodoListCreate(TodoListDTO form){
//        todoListMapper.insertTodoList(form);
//        return "redirect:/todoList";
//    }
//
//    @PostMapping("/todoList/get")
//    @ResponseBody
//    public List<TodoListDTO> TodoListGet(HttpSession session){
//        String user_id = (String) session.getAttribute("user_id");
//        return todoListMapper.getTodoList(user_id);
//    }
//
////    @GetMapping("/map")
////    @ResponseBody
////    public List<Map<String, Object>> getMap(HttpSession session){
////        String user_id = (String) session.getAttribute("user_id");
////        List<Map<String, Object>> showtodo = new ArrayList<>();
////        Map<String, Object> result = todoListMapper.getMap(user_id);
////        showtodo.add(result);
////        return showtodo;
////    }
//    @GetMapping("/map")
//    @ResponseBody
//    public Map<String, Object> getMap(HttpSession session){
//        String user_id = (String) session.getAttribute("user_id");
//        Map<String, Object> result = todoListMapper.getMap(user_id);
//        System.out.println("result = " + result);
//        return result;
//    }
//
//    @GetMapping("/todoList/delete")
//    public String TodoListDelete(@RequestParam("todo_num") int todo_num){
//        todoListMapper.deleteTodoList(todo_num);
//        return "redirect:/todoList";
//    }
