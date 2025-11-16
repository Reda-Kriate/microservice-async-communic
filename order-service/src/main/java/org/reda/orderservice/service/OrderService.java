package org.reda.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.reda.orderservice.dto.OrderLineItemsDto;
import org.reda.orderservice.dto.OrderRequest;
import org.reda.orderservice.model.Order;
import org.reda.orderservice.model.OrderLineItems;
import org.reda.orderservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;


    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> lineItems = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(orderLineItemsDto -> mapFromDto(orderLineItemsDto))
                .toList();
        order.setOrderLineItems(lineItems);
        orderRepo.save(order);
    }

    private OrderLineItems mapFromDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .id(orderLineItemsDto.getId())
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
