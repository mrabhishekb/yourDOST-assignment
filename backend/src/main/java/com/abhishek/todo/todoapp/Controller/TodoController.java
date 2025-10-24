package com.abhishek.todo.todoapp.Controller;

import com.abhishek.todo.todoapp.Model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController{

    private final List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    // GET /todos
    @GetMapping("/")
    public List<Todo> getTodos(){
        return todos;
    }

    // POST /todos → accepts single or multiple todos
    @PostMapping("/")
    public ResponseEntity<?> createTodos(@RequestBody Object body){
        try{
            if (body instanceof List<?>){
                // When multiple todos are posted
                List<?> list = (List<?>) body;
                List<Todo> created = new ArrayList<>();
                for (Object obj : list){
                    if (obj instanceof java.util.LinkedHashMap<?, ?> map){
                        Todo todo = mapToTodo(map);
                        created.add(todo);
                        todos.add(todo);
                    }
                }
                return ResponseEntity.ok(created);
            } else if (body instanceof java.util.LinkedHashMap<?, ?> map){
                // When single todo is posted
                Todo todo = mapToTodo(map);
                todos.add(todo);
                return ResponseEntity.ok(todo);
            } else{
                return ResponseEntity.badRequest().body("Invalid request format");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error parsing request: " + e.getMessage());
        }
    }

    private Todo mapToTodo(java.util.LinkedHashMap<?, ?> map){
        Todo todo = new Todo();
        todo.setId(nextId++);
        todo.setTitle((String) map.get("title"));
        todo.setCompleted(false);
        todo.setDueDate(LocalDate.now().plusDays(1));
        return todo;
    }

    // PUT /todos/:id
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updated){
        for (Todo todo : todos){
            if (todo.getId() == id){
                if (updated.getTitle() != null) todo.setTitle(updated.getTitle());
                if (updated.getDueDate() != null) todo.setDueDate(updated.getDueDate());
                todo.setCompleted(updated.isCompleted());
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /todos/:id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id){
        for (Todo todo : todos){
            if (todo.getId() == id){
                todos.remove(todo);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
