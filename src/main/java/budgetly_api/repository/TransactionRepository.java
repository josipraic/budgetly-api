package budgetly_api.repository;

import budgetly_api.entity.Transaction;
import budgetly_api.entity.TransactionType;
import budgetly_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndType(User user, TransactionType type);
}
