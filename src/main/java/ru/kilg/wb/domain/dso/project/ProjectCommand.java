package ru.kilg.wb.domain.dso.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kilg.wb.domain.project.Note;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * WB001
 * ProjectCommand - [Form object for Project in ProjectController]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 * @see ru.kilg.wb.domain.project.Project
 * @see ru.kilg.wb.controllers.ProjectController
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCommand {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;

    private Note description;

    private List<Task> tasks;


}
