package com.todo.hulkstore.mapper;

import com.todo.hulkstore.domain.PaymentOrder;
import com.todo.hulkstore.domain.ProductOrder;
import com.todo.hulkstore.dto.ProductOrderDTO;
import com.todo.hulkstore.dto.response.PaymentOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public interface PaymentOrderMapper {

    @Mapping(target = "productOrders", source = "productOrders", qualifiedByName = "toProductOrderDTOList")
    PaymentOrderResponse toPaymentOrderResponse(PaymentOrder paymentOrder);

    @Named("toProductOrderDTOList")
    default List<ProductOrderDTO> toProductOrderDTOList(List<ProductOrder> productOrders) {
        if (productOrders == null || productOrders.isEmpty()) return emptyList();
        return productOrders.stream().map(this::toProductOrderDTO).collect(toList());
    }

    @Mapping(target = "productId", source = "productOrder.productDetail.id")
    @Mapping(target = "productName", source = "productOrder.productDetail.name")
    @Mapping(target = "productPrice", source = "productOrder.productDetail.price")
    ProductOrderDTO toProductOrderDTO(ProductOrder productOrder);

    default List<PaymentOrderResponse> toPaymentOrderResponseList(List<PaymentOrder> source) {
        if (source == null || source.isEmpty()) return emptyList();
        return source.stream().map(this::toPaymentOrderResponse).collect(toList());
    }
}
