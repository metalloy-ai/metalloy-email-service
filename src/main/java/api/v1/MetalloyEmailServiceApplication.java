package api.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MetalloyEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetalloyEmailServiceApplication.class, args);
    }

}
