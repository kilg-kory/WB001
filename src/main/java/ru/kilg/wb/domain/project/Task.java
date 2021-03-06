package ru.kilg.wb.domain.project;

import lombok.*;

import javax.persistence.*;


/**
 * WB001
 * Task - [Working task - include description, comments, work status ]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */

@EqualsAndHashCode(callSuper = true, exclude = "project")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "project")
public class Task extends Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Project project;

    private String name;

    private String status;
}



