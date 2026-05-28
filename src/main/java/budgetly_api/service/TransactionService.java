package budgetly_api.service;

import budgetly_api.dto.CategoryRequest;
import budgetly_api.dto.TransactionRequest;
import budgetly_api.entity.Category;
import budgetly_api.entity.Transaction;
import budgetly_api.entity.TransactionType;
import budgetly_api.entity.User;
import budgetly_api.repository.CategoryRepository;
import budgetly_api.repository.TransactionRepository;
import budgetly_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Transaction createTransaction(TransactionRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = Transaction.builder()
                .type(request.getType())
                .amount(request.getAmount())
                .description(request.getDescription())
                .date(request.getDate())
                .category(category)
                .user(user)
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(TransactionType type) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (type != null) {
            return transactionRepository.findByUserAndType(user, type);
        }

        return transactionRepository.findByUser(user);
    }

    public void deleteTransaction(Long id) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this transaction");
        }

        transactionRepository.delete(transaction);
    }
}
