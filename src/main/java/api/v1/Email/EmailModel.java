package api.v1.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "core-email.auth")
public class EmailModel implements Serializable {
    @Id
    @NotNull
    @NotEmpty
    @JsonProperty("user_id")
    private String userId;

    @NotNull
    @NotEmpty
    private String username;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String type;
}