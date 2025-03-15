package com.todolistjava.to_do_list.services;


import com.todolistjava.to_do_list.models.ToDo;
import com.todolistjava.to_do_list.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<ToDo> listarTodos() {
        return todoRepository.findAll();
    }
}
