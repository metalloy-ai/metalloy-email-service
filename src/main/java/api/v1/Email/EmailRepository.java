package api.v1.Email;

import api.v1.Email.EmailModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmailRepository extends ReactiveMongoRepository<EmailModel, String> {

}
