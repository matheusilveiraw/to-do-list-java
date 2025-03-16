# To-Do List API

Este projeto é uma API para gerenciamento de tarefas (*To-Do List*) desenvolvida com **Spring Boot** e **MySQL**.

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
- **MySQL**
- **Maven**
- **Hibernate (JPA)**

## 🎯 MVP do Projeto

A API contará com as seguintes funcionalidades inicialmente:

- **Criar** tarefas (`POST`)
- **Visualizar** tarefas (`GET`)
- **Atualizar** tarefas (`PUT`)
- **Excluir** tarefas (`DELETE`)
- **Marcar e desmarcar** tarefas como concluídas

### 📌 Estrutura básica da tarefa:

Cada tarefa conterá os seguintes atributos inicialmente:

- `id` (identificador único)
- `nome` (título da tarefa)
- `descrição` (detalhes sobre a tarefa)
- `status` (indica se a tarefa está concluída ou não)

## 📜 Log de Atualizações

### 15/03/2025
- **GET**: Implementada a busca de todas as tarefas.
- **POST**: Implementado o endpoint para criação de tarefas.
- **DELETE**: Implementada a exclusão de tarefas.
- **PUT**: Implementada a atualização de tarefas.
- **GET**: Criado endpoint para buscar tarefa individual.
- **PUT**: Implementada a finalização de tarefa (alterando o status para concluída).

---

