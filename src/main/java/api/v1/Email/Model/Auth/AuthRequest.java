package api.v1.Email.Model.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthRequest extends Auth {
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
}