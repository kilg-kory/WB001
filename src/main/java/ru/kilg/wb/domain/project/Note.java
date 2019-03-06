package ru.kilg.wb.domain.project;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * WB001
 * Note - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 05.03.19
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private Date createDate;

}
