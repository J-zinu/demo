package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if(path.contains("/login")) {
            return true;
        } else if (session.getAttribute("login_id") == null) {
            response.sendRedirect("/login");
            return false;
        }


        return true;
    }
}
