package ru.kilg.wb.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.dso.project.ProjectCommand;
import ru.kilg.wb.domain.project.Note;
import ru.kilg.wb.domain.project.Project;

import java.util.Date;

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
        Note description = null;
        if(pc.getDescription() != null){
            description = new Note();
            description.setText(pc.getDescription());
            description.setCreateDate(new Date());
        }


        return new Project(pc.getId(), pc.getName(),  description, pc.getTasks());
    }
}
