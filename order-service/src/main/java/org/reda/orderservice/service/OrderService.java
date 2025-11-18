package org.reda.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.reda.orderservice.dto.InventoryResponse;
import org.reda.orderservice.dto.OrderLineItemsDto;
import org.reda.orderservice.dto.OrderRequest;
import org.reda.orderservice.model.Order;
import org.reda.orderservice.model.OrderLineItems;
import org.reda.orderservice.repository.OrderRepo;
import org.reda.orderservice.webClientConfig.WebClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {


    private final WebClient.Builder webClient;
    private final OrderRepo orderRepo;


    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> lineItems = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(orderLineItemsDto -> mapFromDto(orderLineItemsDto))
                .toList();
        order.setOrderLineItems(lineItems);

        List<String> skucodes = order.getOrderLineItems().stream()
                .map(orderLineItems -> orderLineItems.getSkuCode())
                .toList();

        InventoryResponse[] inventoryResponsesArray = webClient.build().get()
                .uri("http://inventory-service/api/inventory",
                        uribuilder->uribuilder.queryParam("skuCode",skucodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponsesArray != null;
        boolean allProductIsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(inventoryResponse -> inventoryResponse.isPresent());

        if(allProductIsInStock){
            orderRepo.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock, please try again later ....");
        }

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
