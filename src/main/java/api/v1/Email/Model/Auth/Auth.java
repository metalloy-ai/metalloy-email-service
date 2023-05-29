package api.v1.Email.Model.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @NotNull
    @NotEmpty
    @NotBlank
    @Field("user_id")
    @JsonProperty("user_id")
    private String userId;
    @NotNull
    @NotEmpty
    @NotBlank
    private String username;
    @NotNull
    @NotEmpty
    @NotBlank
    private String role;

    public Auth(AuthLog authLog) {
        this.userId = authLog.getUserId();
        this.username = authLog.getUsername();
        this.role = authLog.getRole();
    }
}
