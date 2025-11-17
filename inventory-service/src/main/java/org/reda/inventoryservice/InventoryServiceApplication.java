package org.reda.inventoryservice;

import org.reda.inventoryservice.model.Inventory;
import org.reda.inventoryservice.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(InventoryRepo inventoryRepo){
        return args -> {
            Inventory inventory = Inventory.builder()
                    .skuCode("Iphone_13_RED")
                    .quantity(100)
                    .build();
            Inventory inventory1 = Inventory.builder()
                    .skuCode("Iphone_13_YELLOW")
                    .quantity(0)
                    .build();
            inventoryRepo.save(inventory);
            inventoryRepo.save(inventory1);
        };
    }
}
