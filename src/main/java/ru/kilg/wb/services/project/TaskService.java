package ru.kilg.wb.services.project;

import org.springframework.stereotype.Service;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;
import ru.kilg.wb.repositories.project.TaskRepository;

import java.util.List;

/**
 * WB001
 * TaskService - CRUD Task operations
 *
 * @author KIlG
 * @version 0.1
 * Create 06.03.19
 */

@Service
public class TaskService {

    private TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllProjectTasks(Project project) {
        return taskRepository.findAllByProject(project);
    }

    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }


    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

}
