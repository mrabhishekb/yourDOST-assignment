package com.abhishek.todo.todoapp.Controller;

import com.abhishek.todo.todoapp.Model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    // GET /todos
    @GetMapping("/")
    public List<Todo> getTodos() {
        return todos;
    }

    // POST /todos
    @PostMapping("/")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().isEmpty())
            return ResponseEntity.badRequest().build();

        todo.setId(nextId++);
        if (todo.getDueDate() == null) todo.setDueDate(LocalDate.now().plusDays(1));
        todo.setCompleted(false);

        todos.add(todo);
        return ResponseEntity.ok(todo);
    }

    // PUT /todos/:id
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updated) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
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
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todos.remove(todo);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
