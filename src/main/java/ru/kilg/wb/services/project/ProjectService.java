package ru.kilg.wb.services.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.repositories.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

/**
 * WB001
 * ProjectService - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //TODO: make pageable
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProject(String name) {
        return projectRepository.findByName(name);
    }

    public Project getProject(Long id) {
        Optional<Project> optional = projectRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }


    public Page<Project> getPageProjects(int page, int limit, String order) {
        return projectRepository.findAll(PageRequest.of(page, limit));
    }
}
