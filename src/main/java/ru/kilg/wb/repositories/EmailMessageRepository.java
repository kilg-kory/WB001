package ru.kilg.wb.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kilg.wb.domain.EmailMessage;

public interface EmailMessageRepository extends CrudRepository<EmailMessage, Long> {

}