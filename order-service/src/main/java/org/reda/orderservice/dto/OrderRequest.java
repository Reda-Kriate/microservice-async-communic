package org.reda.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reda.orderservice.model.OrderLineItems;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDto;
}
