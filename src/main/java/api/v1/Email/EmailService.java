package api.v1.Email;

import api.v1.Email.Model.Auth.AuthCreate;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface EmailService {
    Mono<Object> sendAuthEmail(@Valid AuthCreate email);
}
