package api.v1.Email;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface EmailService {
    Mono<EmailModel> sendAuthEmail(@Valid EmailCreate email) throws IOException;

    Mono<EmailModel> verifyEmail(String token, String userId);
}
