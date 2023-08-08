package com.example.demo.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController {

    //ModelAndView의 알림처리를 하려고하면 view페이지에 <p>${errorMessage}</p> 이렇게 추가해줘야함.
//    @GetMapping("/error")
//    public ModelAndView handleError() {
//        ModelAndView mav = new ModelAndView("error"); // "error"는 뷰 이름 (예: error.jsp)
//        mav.addObject("errorMessage", "잘못된 페이지 접근!!!");
//        return mav;
//    }

    @GetMapping("/error")
    public ResponseEntity<HashMap<String, String>> handleError() {
        HashMap<String, String> response = new HashMap<>();
        response.put("errorMessage", "잘못된 페이지 접근입니다!!!");
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/error") //ResponseBody 통해 처리(error.jsp 는 보여주지 않는게 맞다.)
//                            //SpringBoot 기본 에러 처리를 application.properties error.jsp 설정을 하여 보여지는것)
//    @ResponseBody
//    public Map<String, String> handleError() {
//        Map<String, String> response = new HashMap<>();
//        response.put("errorMessage", "잘못된 페이지 접근입니다!!!");
//        return response;
//    }
//
//

//    @GetMapping("/error")
//    public String handleError(Model model) {
//        model.addAttribute("errorMessage",
//                "잘못된 페이지 접근입니다!!!");
//        return "error";
// }
//
//    //Model 형태로 뷰를 처리하면 아래와 같이 추가해줘야합니다 꼭꼭꼭이요!
//    //error.jsp 파일의 <body> 태그에 <p><%= request.getAttribute("errorMessage") %></p> 추가해줘야합니다.

}

