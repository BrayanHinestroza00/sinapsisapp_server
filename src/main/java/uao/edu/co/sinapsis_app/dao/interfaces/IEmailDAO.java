package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.beans.EmailDetails;

public interface IEmailDAO {
    // Method
    // To send a simple email
    void sendEmail(EmailDetails details);

    // Method
    // To send a simple email with multiple dest
    void sendEmailMultiple(EmailDetails details);
}
