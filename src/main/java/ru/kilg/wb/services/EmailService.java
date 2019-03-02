package ru.kilg.wb.services;

import ru.kilg.wb.domain.EmailMessage;

public interface EmailService {
    void sendEmail(EmailMessage emailMessage);
}
