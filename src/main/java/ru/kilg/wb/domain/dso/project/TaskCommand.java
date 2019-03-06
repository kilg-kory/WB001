package ru.kilg.wb.domain.dso.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WB001
 * TaskCommand - [Form object for task]
 *
 * @author KIlG
 * @version 0.1
 * Create 06.03.19
 * @see ru.kilg.wb.domain.project.Task
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCommand {

    private Long id;

    private Long projectId;

    private String name;

    private String status;
}

