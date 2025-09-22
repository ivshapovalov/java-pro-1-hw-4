package edu.t1.javapro1.hw4.dto.incoming.request;

import edu.t1.javapro1.hw4.persistence.enumeration.ProductType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotEmpty(message = "Username must not be null")
        @Pattern(regexp = "^[0-9]{1,20}$", message = "Номер счета должен содержать только цифры")
        String accountNumber,

        @NotNull(message = "Balance must not be null")
        BigDecimal balance,

        @NotNull(message = "Product type must not be null")
        ProductType type,

        @NotNull
        Long userId
) {
}
