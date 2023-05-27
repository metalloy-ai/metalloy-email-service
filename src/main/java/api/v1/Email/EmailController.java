package api.v1.Email;

import api.v1.Utility.APIResponse;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity<APIResponse<Object>>> sendEmail(@Valid @RequestBody EmailCreate email) throws IOException {
        return emailService.sendAuthEmail(email)
                .map(out -> ResponseEntity.ok(new APIResponse<>(HttpStatus.SC_CREATED, "Auth Code sent successfully.", null)))
                .onErrorResume(Mono::error);
    }

    @GetMapping("/verify")
    public Mono<ResponseEntity<APIResponse<Object>>> verifyEmail(@RequestParam String token, @RequestParam String userId) {
        return emailService.verifyEmail(token, userId)
                .map(out -> ResponseEntity.ok(new APIResponse<>(HttpStatus.SC_OK, "Auth Code verified successfully.", null)))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .badRequest()
                        .body(new APIResponse<>(HttpStatus.SC_BAD_REQUEST, "Auth Code verification failed.", null))));
    }
}
