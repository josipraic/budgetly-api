package budgetly_api.service;

import budgetly_api.dto.CategoryRequest;
import budgetly_api.entity.Category;
import budgetly_api.entity.User;
import budgetly_api.repository.CategoryRepository;
import budgetly_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category createCategory(CategoryRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = Category.builder()
                .name(request.getName())
                .user(user)
                .build();

        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return categoryRepository.findByUser(user);
    }
}
