package org.reda.inventoryservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reda.inventoryservice.dto.InventoryResponse;
import org.reda.inventoryservice.model.Inventory;
import org.reda.inventoryservice.repository.InventoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public List<InventoryResponse> skuCodeIsPresent(List<String> skuCodes) throws InterruptedException {
        log.info("INVENTORY CALLED");
//        log.info("Started");
//        Thread.sleep(10000);
//        log.info("Ended");
         return inventoryRepo.findBySkuCodeIn(skuCodes)
                 .stream().map(inventory ->
                         InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isPresent(inventory.getQuantity() > 0)
                            .build()).toList();
    }
}
