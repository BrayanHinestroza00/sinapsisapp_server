package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import uao.edu.co.sinapsis_app.beans.EmailDetails;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmailDAO;
import uao.edu.co.sinapsis_app.services.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class EmailDAO implements IEmailDAO {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmail(EmailDetails details) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            message.setSubject(details.getAsunto());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(/*details.getTo()*/ "brayan.hinestroza@uao.edu.co");
            helper.setText(details.getCuerpoMensaje(), true);
            javaMailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendEmailMultiple(EmailDetails details) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            message.setSubject(details.getAsunto());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(/*details.getMultipleTo()*/ new String[]{"brayan.hinestroza@uao.edu.co", "bahinestroza@uao.edu.co"});
            helper.setText(details.getCuerpoMensaje(), true);
            javaMailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
