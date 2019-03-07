package ru.kilg.wb.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kilg.wb.domain.dso.project.TaskCommand;
import ru.kilg.wb.domain.project.Project;
import ru.kilg.wb.domain.project.Task;

/**
 * WB001
 * TaskCommandToTask - Convert TaskCommand to Task
 *
 * @author KIlG
 * @version 0.1
 * Create 06.03.19
 * @see TaskCommand
 * @see Task
 */

@Component
public class TaskCommandToTask implements Converter<TaskCommand, Task> {
    @Override
    public Task convert(TaskCommand taskCommand) {
        if (taskCommand == null)
            return null;

        Project project = null;
        if (taskCommand.getProjectId() != null) {
            project = new Project();
            project.setId(taskCommand.getProjectId());
        }

        return new Task(taskCommand.getId(), project, taskCommand.getName(), taskCommand.getStatus());
    }
}
