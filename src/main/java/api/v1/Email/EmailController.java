package api.v1.Email;

import api.v1.Email.Model.Auth.AuthRequest;
import api.v1.Utility.APIResponse;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequestMapping("api/v1/auth")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity<APIResponse<Object>>> sendEmail(@Valid @RequestBody AuthRequest authRequest) {
        return emailService.sendAuthEmail(authRequest)
                .map(out -> ResponseEntity
                        .status(HttpStatus.SC_CREATED)
                        .body(new APIResponse<>(HttpStatus.SC_CREATED, "AuthLog Code sent successfully.", null)))
                .onErrorResume(Mono::error);
    }

}
