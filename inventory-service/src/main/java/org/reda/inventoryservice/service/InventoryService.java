package org.reda.inventoryservice.service;


import lombok.RequiredArgsConstructor;
import org.reda.inventoryservice.repository.InventoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {
    private InventoryRepo inventoryRepo;

    public boolean skuCodeIsPresent(String skuCode){
        return inventoryRepo.findBySkuCode(skuCode).isPresent();
    }
}
