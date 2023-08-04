package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // 뷰의 이름 설정
        return modelAndView;
    }

    //String으로 반환하면 뷰의 이름을 반환한다.
    //그러나 이렇게 사용하면 ajax를 사용할 수 없다.

    //MVC 패턴의 장점
    /*
    역할별로 독립적이기 때문에 작업 구분이 명확하고 유지보수가 쉽다.
    모델과 뷰의 분리로 인해 화면의 변경이 자유롭다.
    컴포턴트간 의존성이 최소화 되어 코드의 재사용성도 높다

    MVC 패턴의 단점
    개발 효율이 떨어질 우려가 있다.
    Controller에서 Model에 대한 지원이 너무 커지면 Model의 Controller에 대한
    의존도가 높아져 MVC 패턴의 장점인 기능별 독립성을 유지할 수 없게 될 우려가 있다.

    client가 요청을 보내면 controller가 요청을 받아서 model에게 요청을 보내고
    model은 요청을 받아서 처리한 후에 controller에게 응답을 보내고
    controller는 응답을 받아서 view에게 응답을 보내고
    view는 응답을 받아서 client에게 응답을 보낸다.

    단점을 회피하는 방법
     */


    //라이브러리와 프레임워크에 대해서 알아보자.
    //우선 두개의 차이점은 무엇인가?
    //가장 큰 차이점은 제어의 흐름이 어디에 있는지에 따라 다르다.

    //Http = hyper text transfer protocol => 클라이언트와 서버간 정보를 주고받음
    //Html = hyper text markup language => 웹페이지를 작성하기 위한 마크업 언어
    //하이퍼텍스트 => 웹에서 정보를 탐색하는 방법을 기술하는 용어
    //마크업 언어 => 태그를 이용하여 문서나 데이터의 구조를 명기하는 언어

    //viewResolver에 대해서 무엇인지 알아보자
    //viewResolver는 뷰의 이름을 받아서 뷰의 경로를 반환해주는 역할을 한다.

    //Spring MVC에서는 뷰의 이름을 반환하면 viewResolver가 뷰의 경로를 반환해준다.
    //Spring MVC가 무엇인가?

}
