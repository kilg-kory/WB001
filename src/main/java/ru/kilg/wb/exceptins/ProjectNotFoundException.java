package ru.kilg.wb.exceptins;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * WB001
 * ProjectNotFoundException - [Description]
 *
 * @author KIlG
 * @version 0.1
 * <p>
 * <p>
 * Create 06.03.19
 */


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String s) {
        super(s);
    }


}
