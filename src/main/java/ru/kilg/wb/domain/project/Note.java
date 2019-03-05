package ru.kilg.wb.domain.project;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private Date createDate;

}
