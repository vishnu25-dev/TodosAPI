package dao;

import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utils.DatabaseUtil;
public class TodoDao {

    // Methods to add, update, and delete items
    public List<Item> getAllTodos() {

        List<Item> todos = new ArrayList<>();

        String query = "select * from tododb.todos";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String value = rs.getString("value");
                todos.add(new Item(id, value));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return todos;
    }

    public void addTodo(String id, String value){

        String query = "INSERT INTO tododb.todos (id, value) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setString(1,id);
            ps.setString(2,value);
            ps.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateTodo(String id, String value){

        String query = "UPDATE tododb.todos SET value = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setString(1,value);
            ps.setString(2,id);
            int affectedRows = ps.executeUpdate();

            if( affectedRows == 0){
                throw new RuntimeException("Id not found");
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public void deleteTodo(String id){
        String query = "DELETE FROM tododb.todos WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, id);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Id not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}