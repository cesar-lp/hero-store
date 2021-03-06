package com.herostore.products.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrderLineDTO implements Serializable {

    private static final long serialVersionUID = -2041025772697180892L;

    Long id;
    Long productId;
    String productName;
    BigDecimal productPrice;
    Integer quantity;
    BigDecimal total;
}
