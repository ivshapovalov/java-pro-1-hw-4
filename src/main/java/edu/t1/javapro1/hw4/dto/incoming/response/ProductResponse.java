package edu.t1.javapro1.hw4.dto.incoming.response;

import edu.t1.javapro1.hw4.persistence.enumeration.ProductType;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String accountNumber,
        BigDecimal balance,
        ProductType type,
        Long userId
) {
}
