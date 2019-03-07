package ru.kilg.wb.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kilg.wb.domain.converters.ProjectCommandToProject;
import ru.kilg.wb.domain.dso.project.ProjectCommand;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.services.project.ProjectService;

import javax.validation.Valid;

/**
 * WB001
 * ProjectController - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private ProjectCommandToProject projectCommandToProject;


    public ProjectController(ProjectService projectService, ProjectCommandToProject projectCommandToProject) {
        this.projectService = projectService;
        this.projectCommandToProject = projectCommandToProject;
    }

    @PostMapping("/add")
    public String postAddProject(@Valid ProjectCommand data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project/form";
        }

        Project project = projectService.saveProject(projectCommandToProject.convert(data));
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/add")
    public String getAddProject(Model model) {
        model.addAttribute("pc", new ProjectCommand());
        return "project/form";
    }


    @RequestMapping(value = {""})
    public String getAllProjects(Model model) {
        model.addAttribute("list", projectService.getAllProjects());
        return "project/list";
    }

    @RequestMapping("/page/{page}/{limit}/{order}")
    public String getProjectsPageable(Model model, @PathVariable int page, @PathVariable int limit, @PathVariable String order) {
        if (--page < 0) page = 0;
        Page<Project> projectPage = projectService.getPageProjects(page, limit, order);
        model.addAttribute("list", projectPage.getContent());
        return "project/list";
    }

    @RequestMapping("{id}")
    public String getProject(Model model, @PathVariable Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/one";
    }
}
