package ru.kilg.wb.domain;

import lombok.Data;
import ru.kilg.wb.domain.auth.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class EmailMessage {

    @GeneratedValue
    @Id
    private Long id;

    private String emailTo;

    private String subject;

    private String text;

    private Boolean sent;

    private String errorReport;


    //TODO: Not clean code! Rewrite with call method from service
    public EmailMessage(User user, String subject, String text) {
        this.emailTo = user.getEmail();
        this.subject = subject;
        this.text = text;
    }
}