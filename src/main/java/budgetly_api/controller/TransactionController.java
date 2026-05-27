package budgetly_api.controller;

import budgetly_api.dto.TransactionRequest;
import budgetly_api.entity.Transaction;
import budgetly_api.entity.TransactionType;
import budgetly_api.repository.TransactionRepository;
import budgetly_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping
    public List<Transaction> getTransactions(@RequestParam(required = false)TransactionType type) {
        return transactionService.getTransactions(type);
    }

}
