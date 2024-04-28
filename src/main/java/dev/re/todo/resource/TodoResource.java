package dev.re.todo.resource;

import dev.re.todo.model.Todo;
import dev.re.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TodoResource {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todos = todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> findById(@PathVariable long id){
        Optional<Todo> todoOptional = todoService.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            return ResponseEntity.ok().body(Collections.singletonList(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Todo todo){
        Todo newTodo = todoService.save(todo);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Todo todoRequest) {
        Optional<Todo> todoOptional = todoService.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setThing_to_do(todoRequest.getThing_to_do());
            todo.setCompleted(todoRequest.isCompleted());
            todoService.save(todo);
            return ResponseEntity.ok().body(todo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        todoService.delete(id);
    }
}
