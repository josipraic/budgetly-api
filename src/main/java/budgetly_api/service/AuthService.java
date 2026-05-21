package budgetly_api.service;

import budgetly_api.dto.LoginRequest;
import budgetly_api.dto.RegisterRequest;
import budgetly_api.entity.Role;
import budgetly_api.entity.User;
import budgetly_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if(userRepository.existByEmail(request.getEmail())) {
            throw new RuntimeException("Email allready exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPasssword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return "Login successful";
    }
}
