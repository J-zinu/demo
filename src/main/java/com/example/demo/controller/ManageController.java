// Controller
package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/manage") //개별 핸들러 메소드가 같은 URL 패턴에 매핑된다.
public class ManageController {
    private final ManageService manageService;

    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }
    @GetMapping
    public ModelAndView managePage(HttpSession session) {
        String viewName;
        Map<String, Object> params = new HashMap<>();
        String user_id = (String) session.getAttribute("user_id");
        if (user_id == null) {
            viewName = "login";
        } else {
            viewName = "manage";
            params.put("user_id", user_id);
        }
        return new ModelAndView(viewName, params);
    }

//    @PutMapping("/update") //@RequestMapping 이 존재하므로 ("/update")를 사용할 수 있는건데, 아니라면 ("/manage/update")형태로 작성을 해주어야 합니다.
//    @ResponseBody
//    public Map<String, Object> updateUser(@RequestBody MemberDTO memberDTO) {
//        manageService.updateUser(memberDTO);
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "비밀번호가 성공적으로 변경되었습니다!");
//        result.put("status", "success");
//        return result;
//    }

    @PutMapping("/update")//@RequestMapping 이 존재하므로 ("/update")를 사용할 수 있는건데, 아니라면 ("/manage/update")형태로 작성을 해주어야 합니다.
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody MemberDTO memberDTO){
        int pw_upDate = manageService.updateUser(memberDTO);
        Map<String, Object> result = new HashMap<>();

        if (pw_upDate > 0) {
            result.put("message", "비밀번호가 성공적으로 변경되었습니다.");
            result.put("status", "success");
        }
        else{
            result.put("message", "비밀번호 변경에 실패하였습니다.");
            result.put("status", "fail");
        }
        return result;
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestBody MemberDTO memberDTO){
        int user_Delete =  manageService.deleteUser(memberDTO);
        Map<String, Object> result = new HashMap<>();
        if(user_Delete> 0) {
            result.put("message", "계정이 성공적으로 삭제되었습니다!");
            result.put("status", "success");
        }
        else {
            result.put("message", "TODOLIST 모두 삭제하고," +"\n"+ "다시 회원 탈퇴를 진행해주세요.");
            result.put("status", "fail");
        }
        return result;
    }

//    @DeleteMapping("/delete")
//    @ResponseBody
//    public Map<String, Object> deleteUser(@RequestBody MemberDTO memberDTO) {
//        manageService.deleteUser(memberDTO);
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "계정이 성공적으로 삭제되었습니다!");
//        result.put("status", "success");
//        return result;
//    }

    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "성공적으로 로그아웃되었습니다!");
        response.put("status", "success");
        return response;
    }
}

//세션과 Restful

//// Controller (8월 1일 수정본 ModelAndView를 통해서 user_id를 manage.html로 넘겨줌
//package com.example.demo.controller;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.service.ManageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/manage")
//public class ManageController {
//    private final ManageService manageService;
//
//    public ManageController(ManageService manageService) {
//        this.manageService = manageService;
//    }
////
////
////    @GetMapping
////    public String managePage(HttpSession session) {
////        String user_id = (String) session.getAttribute("user_id");
////        if (user_id == null) {
////            return "login";
////        } else {
////            return "manage";
////        }
////    }
//@GetMapping
//public ModelAndView managePage(HttpSession session) {
//    String viewName;
//    Map<String, Object> params = new HashMap<>();
//    String user_id = (String) session.getAttribute("user_id");
//    if (user_id == null) {
//        viewName = "login";
//    } else {
//        viewName = "manage";
//        params.put("user_id", user_id);
//    }
//    return new ModelAndView(viewName, params);
//}
//
//
//
////    @GetMapping
////    @ResponseBody
////    public Map<String, Object> managePage(HttpSession session) {
////        String user_id = (String) session.getAttribute("user_id");
////
////        Map<String, Object> response = new HashMap<>();
////        if (user_id == null) {
////            response.put("status", "fail");
////            response.put("message", "로그인 페이지로 리다이렉트합니다.");
////        } else {
////            response.put("status", "success");
////            response.put("message", "manage 페이지에 접근했습니다.");
////        }
////        return response;
////    }
//
//    @PostMapping("/update")
//    @ResponseBody
//    public Map<String, Object> updateUser(@RequestBody MemberDTO memberDTO) {
//        manageService.updateUser(memberDTO);
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "비밀번호가 성공적으로 변경되었습니다!");
//        result.put("status", "success");
//        return result;
//    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    public Map<String, Object> deleteUser(@RequestBody MemberDTO memberDTO) {
//        manageService.deleteUser(memberDTO);
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "계정이 성공적으로 삭제되었습니다!");
//        result.put("status", "success");
//        return result;
//    }
//
//    @PostMapping("/logout")
//    @ResponseBody
//    public Map<String, Object> logout(HttpSession session) {
//        session.invalidate();
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "성공적으로 로그아웃되었습니다!");
//        result.put("status", "success");
//        return result;
//    }
//}









//// Controller
//package com.example.demo.controller;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.service.ManageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//@Controller
//@RequestMapping("/manage")
//public class ManageController {
//    private final ManageService manageService;
//
//    public ManageController(ManageService manageService) {
//        this.manageService = manageService;
//    }
//
//    @GetMapping
//    public String managePage(HttpSession session) {
//        String user_id = (String) session.getAttribute("user_id");
//        if (user_id == null) {
//            return "login";
//        } else {
//            return "manage";
//        }
//    }
//    @PostMapping("/update")
//    @ResponseBody
//    public String updateUser(@RequestBody MemberDTO memberDTO) {
//        manageService.updateUser(memberDTO);
//        return "비밀번호가 성공적으로 변경되었습니다!";
//    }
//
//    @PostMapping("/delete")
//    @ResponseBody
//    public String deleteUser(@RequestBody MemberDTO memberDTO) {
//        System.out.println("memberDTO = " + memberDTO);
//        manageService.deleteUser(memberDTO);
//        return "계정이 성공적으로 삭제되었습니다!";
//    }
//
//    @PostMapping("/logout")
//    @ResponseBody
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "성공적으로 로그아웃되었습니다!";
//    }
//}



//--------//

//// Controller
//package com.example.demo.controller;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.service.ManageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/manage")
//public class ManageController {
//    private final ManageService manageService;
//
//    public ManageController(ManageService manageService) {
//        this.manageService = manageService;
//    }
//
//    @GetMapping
//    public String managePage(HttpSession session) {
//        String user_id = (String) session.getAttribute("user_id");
//        if (user_id == null) {
//            // 로그인 페이지 또는 적절한 다른 페이지로 리다이렉트
//            return "redirect:/login"; // "/login"을 실제 로그인 페이지의 URL로 대체하세요
//        } else {
//            return "manage";
//        }
//    }
//    @PostMapping("/update")
//    public String updateUser(@RequestBody MemberDTO memberDTO) {
//        manageService.updateUser(memberDTO);
//        return "redirect:/manage";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestBody MemberDTO memberDTO) {
//        System.out.println("memberDTO = " + memberDTO);
//        manageService.deleteUser(memberDTO);
//        return "redirect:/login";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
//
//}
