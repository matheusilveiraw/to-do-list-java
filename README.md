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
- **GET**: Implementada a busca de todas as tarefas. (/api/todos)
- **POST**: Implementado o endpoint para criação de tarefas. (/api/todos)
- **DELETE**: Implementada a exclusão de tarefas. (/api/todos/id)
- **PUT**: Implementada a atualização de tarefas. (/api/todos/id)
- **GET**: Criado endpoint para buscar tarefa individual. (/api/todos/id)
- **PUT**: Implementada a finalização de tarefa (alterando o status para concluída). (/api/todos/id/finalizar)

---

## 🚀 Tutorial: Como Baixar e Testar o Projeto

### Pré-requisitos

- **Java 17** instalado na sua máquina.
- **Maven** instalado (ou você pode usar o Maven Wrapper, se disponível no projeto).
- **MySQL** instalado e em execução.

### Passo 1: Clonar o Repositório

Abra o terminal e execute o comando abaixo para clonar o repositório:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

### Passo 2: Configurar o Banco de Dados

1. Crie um banco de dados no MySQL, por exemplo, `todo_db`.
2. No projeto, localize o arquivo `application.properties` dentro do diretório `src/main/resources`.
3. Atualize as configurações do banco de dados com suas credenciais.
### Passo 3: Compilar e Executar o Projeto

Navegue até o diretório raiz do projeto e execute um dos comandos abaixo:

- **Utilizando o Maven para rodar a aplicação:**

```bash
mvn spring-boot:run
```

### Compilando o projeto 

```
mvn spring-boot:run
```

Se tudo estiver correto, o projeto já deve estar rodando na sua máquina!