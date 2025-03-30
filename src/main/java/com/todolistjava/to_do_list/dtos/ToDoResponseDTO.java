package com.todolistjava.to_do_list.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolistjava.to_do_list.models.ToDo;
public class ToDoResponseDTO {
    //para saídas
    @JsonProperty("message")
    private String message;

    @JsonProperty("todo")
    private ToDo todo;


    public ToDoResponseDTO(String message, ToDo todo) {
        this.message = message;
        this.todo = todo;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }
}
