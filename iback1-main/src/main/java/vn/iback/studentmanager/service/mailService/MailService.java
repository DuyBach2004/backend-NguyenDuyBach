package vn.iback.studentmanager.service.mailService;

import jakarta.mail.MessagingException;
import vn.iback.studentmanager.dto.DataMailDTO;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
