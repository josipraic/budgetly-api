package budgetly_api.dto;

import budgetly_api.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequest {

    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long categoryId;
}
