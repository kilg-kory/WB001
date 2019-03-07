package ru.kilg.wb.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.project.Note;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;
import ru.kilg.wb.repositories.project.ProjectRepository;

import java.util.ArrayList;

/**
 * WB001
 * ProjectBootstrap - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 06.03.19
 */
@Component
public class ProjectBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProjectRepository projectRepository;

    public ProjectBootstrap(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Project project = new Project();
        project.setName("testprj");
        project.setDescription(new Note());
        project.getDescription().setText("Hello project description");

        Task task = new Task(null, project, "test Task", "CREATED");
        project.setTasks(new ArrayList<>());
        project.getTasks().add(task);

        projectRepository.save(project);

    }
}
