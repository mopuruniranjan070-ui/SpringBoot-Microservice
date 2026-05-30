package com.niranjan.inventoryservice.Controller;

import com.niranjan.commonlib.Dto.InventoryDto;
import com.niranjan.inventoryservice.Entity.Inventory;
import com.niranjan.inventoryservice.Repository.InventoryRepository;
import com.niranjan.inventoryservice.Service.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryMapper inventoryMapper;


    @PostMapping
    public InventoryDto addProduct(@RequestBody InventoryDto inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        Inventory saved = inventoryRepository.save(inventory);
        return inventoryMapper.toDto(saved);
    }

    @GetMapping("{productname}")
    public InventoryDto getstock(@PathVariable String productname) {
        Inventory inventory =  inventoryRepository.findByproductname(productname).orElseThrow(()-> new RuntimeException("Product not found"));
        return inventoryMapper.toDto(inventory);
    }


    @PutMapping("/{productName}/reduce")
    public InventoryDto reducestock(@PathVariable("productName") String productname) {
       Inventory inventory = inventoryRepository.findByproductname(productname).orElseThrow(()-> new RuntimeException("Product not found"));
       if(inventory.getStock()<=0) {
           throw new RuntimeException("out of stock");
       }
       inventory.setStock(inventory.getStock()-1);
       Inventory updatedInventory = inventoryRepository.save(inventory);
       return inventoryMapper.toDto(updatedInventory);
    }

}
