package com.todolistjava.to_do_list.controllers;

import com.todolistjava.to_do_list.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

}
