package api.v1.Email.Model.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "auth")
public class AuthLog extends AuthRequest {
    @Id
    @JsonProperty("_id")
    private String id;
    private String message;
    private Date created;

    public AuthLog(AuthRequest authRequest) {
        this.setUserId(authRequest.getUserId());
        this.setUsername(authRequest.getUsername());
        this.setRole(authRequest.getRole());
        this.setEmail(authRequest.getEmail());
        this.created = new Date();
        this.id = getUserId();
    }
}
