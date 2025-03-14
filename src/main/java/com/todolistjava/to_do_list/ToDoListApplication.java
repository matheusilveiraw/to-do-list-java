package com.todolistjava.to_do_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}
}

//mvp da minha to do list:
//inserir, atualizar, deletar e buscar to dos
//inicialmente a to do vai ter: nome, descrição, status (para finalziar a to do)
