package com.example.demo.controller;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/todoList")
public class TodoListController {

    // DI
    private final TodoListService todoListService;
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public String TodoList(){
        return "todoList";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> TodoListCreate(TodoListDTO form, HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        // 서비스 검사
        if(todoListService.createTodoList(form, user_id) == 1){
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("data", findAllData);
            System.out.println("response = " + response);
        }
        else {
            response.put("error", "CreateController Error!!");
        }
        return response;
    }

    @GetMapping("/read")
    @ResponseBody
    public Map<String, Object> TodoListGet(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        // 로그인 유효성 검사
        if(user_id == null){
            response.put("status", "fail");
            response.put("message", "로그인이 필요합니다.");
        }else{
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("status", "success");
            response.put("data", findAllData);
        }

        return response;
    }

    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> TodoListUpdate(@RequestParam("todo_num") int todo_num, @RequestParam("todo") String todo, HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        // 서비스 검사
        if(todoListService.updateTodoList(todo_num, todo , user_id) == 1){
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("data", findAllData);
        }
        else {
            response.put("error", "UpdateController Error!!");
        }
        return response;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> TodoListDelete(HttpSession session, int todo_num){
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        // 서비스 검사
        if(todoListService.deleteTodoList(todo_num, user_id) == 1){
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("data", findAllData);
        }
        else {
            response.put("error", "DeleteController Error!!");
        }
        return response;
    }

    @PostMapping("/search")
    @ResponseBody
    public Map<String, Object> TodoListSearch(HttpSession session, String todo_search) {
        String user_id = (String) session.getAttribute("user_id");
        List<TodoListDTO> searchData = todoListService.searchTodoList(user_id, todo_search);

        Map<String, Object> response = new HashMap<>();
        response.put("data", searchData);
        return response;
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
