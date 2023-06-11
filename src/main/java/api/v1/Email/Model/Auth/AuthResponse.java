package api.v1.Email.Model.Auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthResponse extends Auth {
    @NotNull
    @NotEmpty
    private Integer code;

    public AuthResponse(AuthRequest authRequest) {
        this.setUserId(authRequest.getUserId());
        this.setUsername(authRequest.getUsername());
        this.setRole(authRequest.getRole());
        this.setData(authRequest.getData());
        this.code = new Random().nextInt(900000) + 100000;
    }
}
