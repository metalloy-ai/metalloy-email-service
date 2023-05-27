package api.v1.Email;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import reactor.core.publisher.Mono;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final Logger LOGGER;
    private final Email FROM;
    private final SendGrid SG;

    @Autowired
    public EmailServiceImpl (
            EmailRepository emailRepository,
            @Value("${SG.FROM}") String from,
            @Value("${SG.API.KEY}") String apiKey
    ) {
        LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
        SG = new SendGrid(apiKey);
        FROM = new Email(from);
        this.emailRepository = emailRepository;
    }
    
    @Override
    public Mono<EmailModel> sendAuthEmail(EmailCreate email) throws IOException {
        String MALCONTENT =
                "<table cellpadding=\"0\" cellspacing=\"0\" style=\"vertical-align: -webkit-baseline-middle; font-size: medium; font-family: Arial;\">" +
                "<tbody>" +
                "<span>Your auth code expires in <b>3 min</b></span>" +
                "<tr>" +
                "<td width=\"190\">" +
                "<img src=\"https://app.logo.com/app/api/v2/images?logo=logo_119e65c0-fd71-40fb-8d96-22216b890a8c&amp;u=2023-04-25T09%3A28%3A22.439Z&amp;format=png&amp;fit=contain&amp;quality=100&amp;margins=100&amp;height=285&amp;width=380\" role=\"presentation\" width=\"190\" style=\"max-width: 190px; display: inline; text-align: left; border-radius: 5px;\">" +
                "</td>" +
                "<td>" +
                "<table cellpadding=\"20\" cellspacing=\"0\" style=\"vertical-align: -webkit-baseline-middle; font-size: medium; font-family: Arial; width: 100%;\">" +
                "<tbody>" +
                "<tr>" +
                "<td>" +
                "<p style=\"margin: 0px; font-size: 15px; font-weight:bold; color: #111; line-height: 20px;\">" +
                "<span>MAIL Service</span>" +
                "</p>" +
                "<p style=\"margin: 0px; color: #687087; font-size: 14px; line-height: 20px;\">" +
                "<span>domain metalloy.tech</span>" +
                "</p>" +
                "<p style=\"margin: 0px; color: #687087; font-size: 14px; line-height: 20px;\">" +
                "</p>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>";
        EmailModel emailModel = new EmailModel(email);
        emailModel.generateCodeAndExpire();
        Email to = new Email(email.getEmail());
        String SUBJECT = "Authorization Code: "+ emailModel.getCode();
        Mail mail = new Mail(FROM, SUBJECT, to, new Content("text/html", MALCONTENT));

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response sendGridResponse = SG.api(request);
        if (sendGridResponse.getStatusCode() >= 200 && sendGridResponse.getStatusCode() < 300) {
            LOGGER.info("EmailModel sent successfully to: {}", email.getEmail());
            return emailRepository.save(emailModel);
        }

        String message = "Error sending email: {}" + sendGridResponse.getBody();
        LOGGER.error(message, sendGridResponse.getBody());
        return Mono.error(new IOException(message));
    }

    @Override
    public Mono<EmailModel> verifyEmail(String token, String userId) {
        return emailRepository.findByCodeAndId(token, userId);
    }

}
