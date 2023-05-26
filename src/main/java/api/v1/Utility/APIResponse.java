package api.v1.Utility;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    //TODO: Make Response body time out for email code
    private int code;
    private String message;
    private T body;
}
