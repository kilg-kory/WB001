package ru.kilg.wb.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;

import java.util.List;

/**
 * WB001
 * TaskRepository - [Description]
 *
 * @author KIlG
 * @version 0.1
 * <p>
 * 06.03.19
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProject(Project project);
}
