package br.com.kauan.desafio_todo_list.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import br.com.kauan.desafio_todo_list.DesafioTodoListApplication;
import br.com.kauan.desafio_todo_list.entity.Todo;
import br.com.kauan.desafio_todo_list.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository, DesafioTodoListApplication desafioTodoListApplication) { //isso é uma injeçao com o construtor, não sei oque é isso
        this.todoRepository = todoRepository; //isso é uma injeçao com o construtor, não sei oque é isso
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list(); //esse list é o metodo a baixo que já contem as instruções de sorting
    }

    //-------------------------------------------------------------------------------------------------------------------------
    //definimos como os dados sevem ser ordenados e guardamos em uma variavel 
    //no banco vamos fazer a consulta da prioridade de forma decendente, e de forma alfabetica
    //quando retornamos o todoRepository com a extenção .findAll(sort) solicitamos uma busca no banco com o parametro definido
    //tanto "nome" quanto "descrição" são variaveis que definimos em "Todo"
    //-------------------------------------------------------------------------------------------------------------------------
    public List<Todo> list()
    {
      Sort sort =  Sort.by("prioridade").descending().and(
            Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }
    
    public List<Todo> update(Todo todo) //recebemos a Todo que vai ser atualizada
    {
        todoRepository.save(todo); //chamamos a variavel do construtor e da conexão do banco 
        return list();             //e passamos o parametro que vai ser atualizado
    }

    public List<Todo> delete(long id)
    {
        todoRepository.deleteById(id);;
        return list(); //esse list é o metodo a baixo que já contem as instruções de sorting
    }
}


 