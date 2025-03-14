package com.todolistjava.to_do_list.services;


import com.todolistjava.to_do_list.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
}
