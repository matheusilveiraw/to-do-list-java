package com.todolistjava.to_do_list.controllers;

import com.todolistjava.to_do_list.dtos.ErrorResponseDTO;
import com.todolistjava.to_do_list.dtos.ToDoRequestDTO;
import com.todolistjava.to_do_list.dtos.ToDoResponseDTO;
import com.todolistjava.to_do_list.models.ToDo;
import com.todolistjava.to_do_list.services.TodoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> buscarToDoPorId(@PathVariable UUID id) {
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
    public ResponseEntity<?> deletarToDo(@PathVariable UUID id) {
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

    // Seu endpoint PUT já existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarToDo(@PathVariable UUID id, @Valid @RequestBody ToDoRequestDTO todoDTO) {
        try {
            ToDo toDoAtualizado = todoService.atualizarToDo(id, todoDTO);
            return ResponseEntity.ok(new ToDoResponseDTO("To do editado com sucesso", toDoAtualizado));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Erro ao atualizar to do: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

// JSON DO PUT
//    {
//        "nome": "nome real da tarefa",
//            "descricao": "fazer várias coisas"
//    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarToDo(@PathVariable UUID id) {
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

    // Método para capturar as exceções de validação e usar o ErrorResponseDTO existente
    //Se algum campo não cumprir as regras de validação (@valid) o Spring lança uma MethodArgumentNotValidException, chamando a função abaixo
    @ExceptionHandler(MethodArgumentNotValidException.class) // Indica que este método vai tratar exceções do tipo MethodArgumentNotValidException lançadas nos controllers.
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) { // Método que recebe a exceção de validação ocorrida
        String errors = ex.getBindingResult() // Obtém o BindingResult que contém os detalhes dos erros de validação
                .getFieldErrors() // Recupera uma lista dos erros relacionados aos campos (FieldErrors)
                .stream() // Cria um fluxo (stream) para processar os erros
                .map(error -> error.getDefaultMessage()) // Mapeia cada erro para sua mensagem padrão (a mensagem definida em @NotBlank, por exemplo)
                .collect(Collectors.joining(", ")); // Junta todas as mensagens de erro em uma única String, separando-as por ", "

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errors, HttpStatus.BAD_REQUEST.value()); // Cria um objeto ErrorResponseDTO passando a mensagem consolidada e o código de status 400
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // Retorna uma ResponseEntity com o objeto de erro e o status HTTP 400 (Bad Request)
    }
}