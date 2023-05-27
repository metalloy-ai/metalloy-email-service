package api.v1.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "auth")
public class EmailModel extends EmailCreate {
    @Id
    @JsonProperty("_id")
    private String id;
    private String code;
    @Indexed(name = "expire_1", expireAfterSeconds = 180)
    private Date expire;

    public EmailModel(EmailCreate emailCreate) {
        super(emailCreate.getUserId(), emailCreate.getUsername(), emailCreate.getEmail());
        this.id = getUserId();
    }

    public void generateCodeAndExpire() {
        this.code = String.valueOf(new Random().nextInt(900000) + 100000);
        this.expire = new Date();
    }
}
