package ru.kilg.wb.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kilg.wb.domain.project.Project;

/**
 * WB001
 * ProjectRepository - [Project paginated rep]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String name);
}
