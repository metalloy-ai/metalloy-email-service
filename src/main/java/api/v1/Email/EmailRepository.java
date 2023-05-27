package api.v1.Email;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmailRepository extends ReactiveMongoRepository<EmailModel, String> {
    Mono<EmailModel> findByCodeAndId(String code, String userId);
}
