package org.reda.inventoryservice.controller;


import lombok.RequiredArgsConstructor;
import org.reda.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    public boolean isInStock(@PathVariable("sku-code") String skuCode ){
        return inventoryService.skuCodeIsPresent(skuCode);
    }
}
