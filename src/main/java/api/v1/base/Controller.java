package api.v1.base;

import api.v1.Utility.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @GetMapping("")
    public APIResponse<Void> BaseHandler() {
        return new APIResponse<>(200, "Welcome to the API", null);
    }
}
