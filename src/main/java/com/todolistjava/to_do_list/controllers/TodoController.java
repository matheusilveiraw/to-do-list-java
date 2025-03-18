package com.todolistjava.to_do_list.controllers;

import com.todolistjava.to_do_list.models.ToDo;
import com.todolistjava.to_do_list.services.TodoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/todos")
@CrossOrigin(origins = "http://localhost:4200") //p/ permitir requisições no angular

public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<?> listarToDos() {
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
            response.put("message", "Erro ao listar to dos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarToDoPorId(@PathVariable Long id) {
        try {
            Optional<ToDo> toDo = todoService.buscarPorId(id);

            if (toDo.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "To Do com ID " + id + " não encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(toDo.get());
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Erro ao buscar o to do: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarToDo (@RequestBody ToDo toDo){
        Map<String, Object> response = new HashMap<>();

        try {
            todoService.criarTodo(toDo);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "To do criado com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Erro ao criar o to do: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    response);
        }
    }

    /* JSON DO POST
 {
    {
    "nome": "teste",
    "descricao": "teste teste teste"
    }
}
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarToDo(@PathVariable Long id) {
        try {
            Optional<ToDo> todoExistente = todoService.buscarPorId(id);

            if (todoExistente.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "To do com ID " + id + " não encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            todoService.deletarTodo(id);

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "To do deletado com sucesso.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Erro ao deletar to do: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarToDo(@PathVariable Long id, @RequestBody ToDo todoAtualizado) {
        try {
            ToDo toDo = todoService.atualizarToDo(id, todoAtualizado);
            return ResponseEntity.ok(toDo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To do não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar to do: " + e.getMessage());
        }
    }
// JSON DO PUT
//    {
//        "nome": "nome real da tarefa",
//            "descricao": "fazer várias coisas"
//    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarToDo(@PathVariable Long id) {
        try {
            ToDo toDoFinalizado = todoService.finalizarToDo(id);
            return ResponseEntity.ok(toDoFinalizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To do não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao finalizar to do: " + e.getMessage());
        }
    }

    @GetMapping("/fazer")
    public ResponseEntity<?> buscarTodosFazer() {
        return this.buscarTodosPorStatus(false);
    }

    @GetMapping("/feitos")
    public ResponseEntity<?> buscarTodosFeitos() {
        return this.buscarTodosPorStatus(true);
    }

    public ResponseEntity<?> buscarTodosPorStatus(boolean status) {
        //retornar somente os to dos com status = false
        try {
            List<ToDo> todos = todoService.listarToDosPorStatus(status);

            if (todos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Erro ao listar to dos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}