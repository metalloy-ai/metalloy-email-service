package api.v1.Email;

import java.io.IOException;
import java.time.Duration;

import api.v1.Email.Model.Auth.AuthCreate;
import api.v1.Email.Model.Auth.AuthLog;
import api.v1.Utility.EmailUtilities;
import api.v1.Email.Model.Auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
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
    private final ReactiveRedisOperations<String, Auth> redisOperations;

    @Autowired
    public EmailServiceImpl (
            EmailRepository emailRepository,
            @Value("${SG.FROM}") String from,
            @Value("${SG.API.KEY}") String apiKey,
            ReactiveRedisOperations<String, Auth> redisOperations
    ) {
        LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
        FROM   = new Email(from);
        SG     = new SendGrid(apiKey);
        this.redisOperations = redisOperations;
        this.emailRepository = emailRepository;
    }

    private Mono<Response> sendEmailWithSendGrid(Mail mail) {
        return Mono.fromCallable(() -> {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            return SG.api(request);
        }).onErrorMap(IOException.class, RuntimeException::new);
    }

    @Override
    public Mono<Object> sendAuthEmail(AuthCreate authCreate) {
        AuthLog authLog  = new AuthLog(authCreate);
        Auth    authSave = new Auth(authLog);
        authLog.generateCode();

        Email   TO      = new Email(authLog.getEmail());
        String  SUBJECT = "Authorization Code: "+ authLog.getCode();
        Content CONTENT = new Content("text/html", EmailUtilities.generateSendBody());
        Mail    mail    = new Mail(FROM, SUBJECT, TO, CONTENT);

        return redisOperations.opsForValue()
                .set(authLog.getCode(), authSave, Duration.ofMinutes(3))
                .flatMap(out -> sendEmailWithSendGrid(mail)
                        .flatMap(sendGridResponse -> {
                            int statusCode = sendGridResponse.getStatusCode();
                            boolean success = statusCode >= 200 && statusCode < 300;

                            String message;
                            if (success) {
                                message = String.format("AuthLog sent successfully to: %s", authLog.getEmail());
                                LOGGER.info(message);
                            } else {
                                message = String.format("Error sending email: %s", sendGridResponse.getBody());
                                LOGGER.error(message);
                            }
                            authLog.setMessage(message);

                            return emailRepository.save(authLog)
                                    .then((success) ?
                                            Mono.just(authLog) :
                                            Mono.error(new IOException(sendGridResponse.getBody())));
                        }));
    }
}
