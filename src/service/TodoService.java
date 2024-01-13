package service;

import dao.TodoDao;
import model.Item;
import java.util.List;

public class TodoService {
    private final TodoDao todoDao = new TodoDao();

    public List<Item> getAllDao(){
        return todoDao.getAllTodos();
    }

    public void addDao(String id, String value){
        todoDao.addTodo(id,value);
    }

    public void updateDao(String id, String value){
        todoDao.updateTodo(id,value);
    }

    public void deleteDao(String id){
        todoDao.deleteTodo(id);
    }
}
