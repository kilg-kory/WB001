package ru.kilg.wb.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.dso.project.ProjectCommand;
import ru.kilg.wb.domain.project.Project;

/**
 * WB001
 * ProjectCommandToProject - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */

@Component
public class ProjectCommandToProject implements Converter<ProjectCommand, Project> {

    @Override
    public Project convert(ProjectCommand pc) {
        return new Project(pc.getId(), pc.getName(),  pc.getDescription(), pc.getTasks());
    }
}
