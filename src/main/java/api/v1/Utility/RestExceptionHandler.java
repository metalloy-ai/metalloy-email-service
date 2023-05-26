package api.v1.Utility;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException e) {
        APIResponse<Void> errorResponse = new APIResponse(400, e.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
