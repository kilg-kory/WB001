package ru.kilg.wb.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.dso.project.ProjectCommand;
import ru.kilg.wb.domain.project.Project;

/**
 * WB001
 * ProjectToProjectCommand - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */

@Component
public class ProjectToProjectCommand implements Converter<Project, ProjectCommand> {
    @Override
    public ProjectCommand convert(Project p) {

        return new ProjectCommand(p.getId(), p.getName(), p.getDescription().getText(), p.getTasks());
    }
}
