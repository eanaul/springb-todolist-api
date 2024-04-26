package dev.re.todo.service;

import dev.re.todo.model.Todo;
import dev.re.todo.repo.TodoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;

    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return todoRepo.findById(id);
    }

    public Todo save(Todo todo) {
        return todoRepo.save(todo);
    }

    public Todo update(Todo todo) {
        return todoRepo.save(todo);
    }

    public void delete(Long id) {
        todoRepo.deleteById(id);
    }
}
