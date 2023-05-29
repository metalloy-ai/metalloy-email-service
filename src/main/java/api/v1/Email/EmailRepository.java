package api.v1.Email;

import api.v1.Email.Model.Auth.AuthLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends ReactiveMongoRepository<AuthLog, String> { }
