package api.v1.Email.Model.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "auth")
public class AuthLog extends AuthCreate {
    @Id
    @JsonProperty("_id")
    private String id;
    private String code;
    private String message;
    private Date created;

    public AuthLog(AuthCreate authCreate) {
        this.setUserId(authCreate.getUserId());
        this.setUsername(authCreate.getUsername());
        this.setRole(authCreate.getRole());
        this.setEmail(authCreate.getEmail());
        this.created = new Date();
        this.id = getUserId();
    }

    public void generateCode() {
        this.code = String.valueOf(new Random().nextInt(900000) + 100000);
    }
}
