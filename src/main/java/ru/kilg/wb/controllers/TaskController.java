package ru.kilg.wb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kilg.wb.domain.converters.TaskCommandToTask;
import ru.kilg.wb.domain.dso.project.TaskCommand;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;
import ru.kilg.wb.exceptins.ProjectNotFoundException;
import ru.kilg.wb.services.project.ProjectService;
import ru.kilg.wb.services.project.TaskService;

/**
 * WB001
 * TaskController - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 06.03.19
 */
@Controller
@RequestMapping("/project/{projectId}/task")
public class TaskController {

    private TaskService taskService;
    private ProjectService projectService;
    private TaskCommandToTask taskCommandToTask;


    public TaskController(TaskService taskService, ProjectService projectService, TaskCommandToTask taskCommandToTask) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.taskCommandToTask = taskCommandToTask;
    }

    @RequestMapping("/all")
    public String getAllProjectTasks(Model model, @PathVariable Long projectId) {
        Project project = projectService.getProject(projectId);
        if (project == null)
            throw new ProjectNotFoundException("Project Not found");

        model.addAttribute("list", project.getTasks());
        return "project/tasks/list";
    }

    @RequestMapping("{id}")
    public String getTask(Model model, @PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "project/tasks/one";
    }


    @GetMapping("/add")
    public String addTask(Model model ,@PathVariable Long projectId){
        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setProjectId(projectId);

        model.addAttribute("tc", taskCommand);
        return "project/tasks/form";
    }

    @PostMapping("/add")
    public String addTask(TaskCommand taskCommand, @PathVariable Long projectId) {
        if (taskCommand.getProjectId() == null) {
            taskCommand.setProjectId(projectId);
        }
        taskService.saveTask(taskCommandToTask.convert(taskCommand));
        return "redirect:/project/" + projectId + "/task";
    }
}
