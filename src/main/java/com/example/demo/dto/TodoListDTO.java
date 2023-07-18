package com.example.demo.dto;

import lombok.Data;

@Data
public class TodoListDTO {
    private int todo_num;
    private String todo;
    private String regist_day;
    private String user_id;
}
