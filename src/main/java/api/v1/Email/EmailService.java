package api.v1.Email;

import api.v1.Utility.APIResponse;

public interface EmailService {
    APIResponse<Void> sendEmail(EmailModel email);
}
