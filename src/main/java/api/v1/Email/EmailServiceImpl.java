package api.v1.Email;

import java.io.IOException;

import api.v1.Utility.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final Email FROM = new Email(System.getenv("SG.FROM"));
    private static final String SUBJECT = System.getenv("SG.SUBJECT");
    private static final Content CONTENT = new Content("text/plain", "This is test email.");

    @Autowired
    public EmailServiceImpl (EmailRepository emailRepository) { this.emailRepository = emailRepository; }
    
    @Override
    public APIResponse<Void> sendEmail(EmailModel email) {
        Email to = new Email(email.getEmail());
        Mail mail = new Mail(FROM, SUBJECT, to, CONTENT);
        //mail.setTemplateId(System.getenv("SG.TEMPLATE.ID"));

        SendGrid sg = new SendGrid(System.getenv("SG.API.KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response sendGridResponse = sg.api(request);
            if (sendGridResponse.getStatusCode() >= 200 && sendGridResponse.getStatusCode() < 300) {
                APIResponse<Void> response = new APIResponse<>(201, "Successfully sent email.", null);
                return response;
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            APIResponse<Void> error = new APIResponse<>(500, "Failed to send email.", null);
            return error;
        }
    }

}
