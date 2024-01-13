package dao;

import model.Item;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final List<Item> todos = new ArrayList<>();
    private Item temp;

    // Methods to add, update, and delete items
    public List<Item> getAllTodos() {
        return todos;
    }

    public void addTodo(String id, String value){
        todos.add(new Item(id,value));
    }

    public void updateTodo(String id, String value){

        temp = new Item(id, null);

        if(todos.isEmpty() || !todos.contains(temp)){
            throw new IllegalArgumentException("Id not found");
        }

        for(Item item : todos){
            if(item.getId().equals(id)){
                item.setValue(value);
                break;
            }
        }

    }

    public void deleteTodo(String id){
        temp = new Item(id, null);

        if(todos.isEmpty() || !todos.contains(temp)){
            throw new IllegalArgumentException("Id not found");
        }

        for(Item item : todos){
            if(item.getId().equals(id)){
                todos.remove(item);
                break;
            }
        }

    }

}