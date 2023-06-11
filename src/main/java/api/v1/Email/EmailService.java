package api.v1.Email;

import api.v1.Email.Model.Auth.AuthRequest;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface EmailService {
    Mono<Object> sendAuthEmail(@Valid AuthRequest email);
}
