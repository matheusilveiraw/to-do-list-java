package com.todolistjava.to_do_list.controllers;

import com.todolistjava.to_do_list.models.ToDo;
import com.todolistjava.to_do_list.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<ToDo> todos = todoService.listarTodos();

            if (todos.isEmpty()) {
            }

            if (todos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                //retornando 204 (funcionou mas tá vazio, por padrão o back não pode retornar nada, tratar no front)
            }

            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Erro ao listar usuários: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
