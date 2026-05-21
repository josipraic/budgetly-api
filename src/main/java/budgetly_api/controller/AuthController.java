package budgetly_api.controller;

import budgetly_api.dto.AuthResponse;
import budgetly_api.dto.LoginRequest;
import budgetly_api.dto.RegisterRequest;
import budgetly_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String message = authService.register(request);

        return new AuthResponse(message);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String message = authService.login(request);

        return new AuthResponse(message);
    }

}
