package com.todolistjava.to_do_list.services;


import com.todolistjava.to_do_list.models.ToDo;
import com.todolistjava.to_do_list.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<ToDo> listarTodos() {
        return todoRepository.findAll();
    }

    public ToDo criarTodo(ToDo toDo) {
        return todoRepository.save(toDo);
    }

    public Optional<ToDo> buscarPorId(Long id) {
        return todoRepository.findById(id);
    }

    public void deletarTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public ToDo atualizarToDo(Long id, ToDo toDoAtualizado) {
        return todoRepository.findById(id).map(usuario -> {
            usuario.setNome(toDoAtualizado.getNome());
            usuario.setDescricao(toDoAtualizado.getDescricao());
            return todoRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("To do não encontrado"));
    }
}
