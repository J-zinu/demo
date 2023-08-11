package com.example.demo.controller;

import com.example.demo.dto.TodoListDTO;
import com.example.demo.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/todoList")

public class TodoListController {

//    @Autowired
//    TodoListService todoListService;

    // DI
    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

//    @Autowired
//    public void setTodoListService(TodoListService todoListService) {
//        this.todoListService = todoListService;
//    }

//    @GetMapping
//    public String TodoList(){
//        return "todoList";
//    }

    @GetMapping
    public ModelAndView content(HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");

        // 데이터와 뷰를 동시에 설정이 가능
        ModelAndView mv = new ModelAndView();

        // 로그인 유효성 검사
        if (user_id == null) {
            mv.setViewName("login"); // 뷰의 이름
            mv.addObject("test", "반환값"); // 뷰로 보낼 데이터 값
        } else {
            mv.setViewName("todoList"); // 뷰의 이름
            mv.addObject("user_id", user_id);
            // mv.addObject("변수 이름", "데이터 값"); 형태를 사용한 메소드로 데이터를보냄
        }

        return mv; //ModelAndView 객체를 반환
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> TodoListCreate(TodoListDTO form, HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        if (user_id != null) {
            response.putAll(todoListService.createTodoList(form));
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("status", "success");
            response.put("data", findAllData);
        } else {
            response.put("status", "아이디없음");
        }
        return response;
    }

    @GetMapping("/read")
    @ResponseBody
    public Map<String, Object> TodoListGet(HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
        response.put("status", "success");
        response.put("data", findAllData);
        return response;
    }

    // @RequestParam("todo_num")  안써도 인식이됨
    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> TodoListUpdate(int todo_num, String new_todo, HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        System.out.println("todo_num = " + todo_num);
        System.out.println("new_todo = " + new_todo);

        // 서비스 검사
        if (user_id != null) {
            todoListService.updateTodoList(todo_num, new_todo, user_id);
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("status", "success");
            response.put("data", findAllData);
        } else {
            response.put("status", "아이디없음");
        }
        return response;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> TodoListDelete(HttpSession session, int todo_num) {
        String user_id = (String) session.getAttribute("user_id");
        Map<String, Object> response = new HashMap<>();

        // 서비스 검사
        if (user_id != null) {
            todoListService.deleteTodoList(todo_num, user_id);
            List<TodoListDTO> findAllData = todoListService.findAllList(user_id);
            response.put("status", "success");
            response.put("data", findAllData);
        } else {
            response.put("status", "아이디없음");
        }
        return response;
    }

    @PostMapping("/search")
    @ResponseBody
    public Map<String, Object> TodoListSearch(HttpSession session, String todo_search) {
        String user_id = (String) session.getAttribute("user_id");
//        Map<String, Object> response = new HashMap<>();
        Map<String, Object> response = todoListService.searchTodoList(user_id, todo_search);

        if ((boolean) response.get("serviceBool")) {
            response.put("controllerBool", true);
            if (user_id != null) {
                todoListService.searchTodoList(user_id, todo_search);
                response.put("status", "success");
            } else {
                response.put("status", "아이디없음");
            }
        }
        return response;
    }
}

// 레파지토리에서 작업했던 부분 (소스코드는 크게 바뀌지 않았음)
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