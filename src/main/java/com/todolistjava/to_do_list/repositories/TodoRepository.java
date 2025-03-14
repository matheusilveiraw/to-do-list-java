package com.todolistjava.to_do_list.repositories;


import com.todolistjava.to_do_list.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {
}
