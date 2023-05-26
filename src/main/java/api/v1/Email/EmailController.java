package api.v1.Email;

import api.v1.Utility.APIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Object> sendEmail(@Valid @RequestBody EmailModel email) {
        APIResponse<Void> result = emailService.sendEmail(email);
        return ResponseEntity.ok(result);
    }

}
