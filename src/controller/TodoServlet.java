package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import service.TodoService;
import org.json.JSONObject;
import utils.JsonUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/api/todo")
public class TodoServlet extends HttpServlet{

    private final TodoService todoService = new TodoService();
    private JSONObject jsonResponse;
    private JSONObject jsonObject;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        jsonResponse = new JSONObject();

        jsonResponse.put("status","success");
        jsonResponse.put("data",todoService.getAllDao());

        sendResponse(response, jsonResponse, HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. convert to json object
        jsonObject = JsonUtils.convertToJSONObject(request);
        jsonResponse = new JSONObject();

        try{
            String id = jsonObject.getString("id");
            String value = jsonObject.getString("todo");
            todoService.addDao(id,value);

            jsonResponse.put("status","success");
            jsonResponse.put("message","todo added successfully");

            sendResponse(response, jsonResponse, HttpServletResponse.SC_OK);
        }catch (Exception e){
            handleException(response, e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        jsonObject = JsonUtils.convertToJSONObject(request);
        jsonResponse = new JSONObject();

        try{
            String id = jsonObject.getString("id");
            String value = jsonObject.getString("todo");
            todoService.updateDao(id,value);

            jsonResponse.put("status","success");
            jsonResponse.put("message","todo updated successfully");
            sendResponse(response, jsonResponse, HttpServletResponse.SC_OK);
        }catch (Exception e){
            handleException(response, e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        jsonObject = JsonUtils.convertToJSONObject(request);
        jsonResponse = new JSONObject();

        try{
            String id = jsonObject.getString("id");
            todoService.deleteDao(id);

            jsonResponse.put("status","success");
            jsonResponse.put("message","todo deleted successfully");
            sendResponse(response, jsonResponse, HttpServletResponse.SC_OK);
        }catch (Exception e){
            handleException(response, e);
        }
    }

    private void sendResponse(HttpServletResponse response, JSONObject jsonResponse, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("status", "error");
        errorResponse.put("message", e.getMessage());
        sendResponse(response, errorResponse, HttpServletResponse.SC_BAD_REQUEST);
    }
}
