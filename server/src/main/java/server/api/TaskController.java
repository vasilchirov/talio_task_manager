package server.api;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import commons.Task;
import server.database.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    /**
     * The repository object that provides database access for Task objects.
     */
    private final TaskRepository taskRepository;

    /**
     * Constructor for TaskController.
     * @param taskRepository The TaskRepository object to be used for database access.
     */

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Endpoint for adding a new task.
     * @param task The Task object to be added to the database.
     * @return ResponseEntity with the status code whether it's success or failure.
     */

    @PostMapping(path = { "", "/" })
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task == null || task.title == null || task.list == null || task.title.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(task);
    }

    /**
     * Endpoint for retrieving all tasks.
     * @return List of Task objects retrieved from the database.
     */


    @GetMapping(path = { "", "/" })
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Endpoint for deleting a task by ID.
     * @param id The ID of the Task to be deleted.
     * @return ResponseEntity with the status code whether it's success or failure.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") long id) {
        if (id < 0 || !taskRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskRepository.getById(id);

        taskRepository.deleteById(id);

        return ResponseEntity.ok(task);
    }
}

