package ru.kilg.wb.services;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.kilg.wb.domain.EmailMessage;
import ru.kilg.wb.repositories.EmailMessageRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private EmailMessageRepository emailMessageRepository;

    public EmailServiceImpl(JavaMailSender mailSender, EmailMessageRepository emailMessageRepository) {
        this.mailSender = mailSender;
        this.emailMessageRepository = emailMessageRepository;
    }


    @Async
    @Override
    public void sendEmail(EmailMessage email) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email.getEmailTo());
            helper.setText(email.getText());
            helper.setSubject(email.getSubject());


            email.setSent(true);
            mailSender.send(message);
            emailMessageRepository.save(email);
            System.err.println("Message sent");

        } catch (MailException | MessagingException ex) {
            email.setSent(false);
            email.setErrorReport(ex.getMessage());
            emailMessageRepository.save(email);
            //TODO: Logger log error
        }


    }
}
