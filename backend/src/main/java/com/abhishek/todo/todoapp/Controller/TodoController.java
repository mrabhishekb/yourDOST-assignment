package com.abhishek.todo.todoapp.Controller;

import com.abhishek.todo.todoapp.Model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController{

    private final List<Todo> todos = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("todos.json");
    private int nextId = 1;

    public TodoController(){
        mapper.registerModule(new JavaTimeModule()); // Important for LocalDate
        loadTodos();
    }

    private void loadTodos(){
        if (file.exists()) {
            try {
                List<Todo> savedTodos = mapper.readValue(file, new TypeReference<List<Todo>>() {});
                todos.addAll(savedTodos);
                if (!todos.isEmpty()) {
                    nextId = todos.get(todos.size() - 1).getId() + 1;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void saveTodos(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, todos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public List<Todo> getTodos(){
        return todos;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTodo(@RequestBody Object input){
        if (input instanceof List<?>){
            // Handle array of todos
            List<?> rawList = (List<?>) input;
            List<Todo> addedTodos = new ArrayList<>();
            for (Object obj : rawList){
                if (obj instanceof Map){
                    Map<?, ?> map = (Map<?, ?>) obj;
                    String title = (String) map.get("title");
                    if (title == null || title.isEmpty()) continue;
                    Todo todo = new Todo();
                    todo.setId(nextId++);
                    todo.setTitle(title);
                    todo.setCompleted(false);
                    todo.setDueDate(LocalDate.now().plusDays(1));
                    todos.add(todo);
                    addedTodos.add(todo);
                }
            }
            saveTodos();
            return ResponseEntity.ok(addedTodos);
        } else if (input instanceof Map){
            // Handle single todo
            Map<?, ?> map = (Map<?, ?>) input;
            String title = (String) map.get("title");
            if (title == null || title.isEmpty())
                return ResponseEntity.badRequest().build();
            Todo todo = new Todo();
            todo.setId(nextId++);
            todo.setTitle(title);
            todo.setCompleted(false);
            todo.setDueDate(LocalDate.now().plusDays(1));
            todos.add(todo);
            saveTodos();
            return ResponseEntity.ok(todo);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updated){
        for (Todo todo : todos){
            if (todo.getId() == id){
                if (updated.getTitle() != null) todo.setTitle(updated.getTitle());
                if (updated.getDueDate() != null) todo.setDueDate(updated.getDueDate());
                todo.setCompleted(updated.isCompleted());
                saveTodos();
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id){
        for (Todo todo : todos){
            if (todo.getId() == id){
                todos.remove(todo);
                saveTodos();
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
