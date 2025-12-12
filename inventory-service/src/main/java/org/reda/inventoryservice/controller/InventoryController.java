package org.reda.inventoryservice.controller;


import lombok.RequiredArgsConstructor;
import org.reda.inventoryservice.dto.InventoryResponse;
import org.reda.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode ) throws InterruptedException {
        return inventoryService.skuCodeIsPresent(skuCode);
    }
}
