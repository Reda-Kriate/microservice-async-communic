package org.reda.inventoryservice.service;


import lombok.RequiredArgsConstructor;
import org.reda.inventoryservice.dto.InventoryResponse;
import org.reda.inventoryservice.model.Inventory;
import org.reda.inventoryservice.repository.InventoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public List<InventoryResponse> skuCodeIsPresent(List<String> skuCodes){
         return inventoryRepo.findBySkuCodeIn(skuCodes)
                 .stream().map(inventory ->
                         InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isPresent(inventory.getQuantity() > 0)
                            .build()).toList();
    }
}
