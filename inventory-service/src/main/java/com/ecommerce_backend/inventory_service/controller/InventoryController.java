package com.ecommerce_backend.inventory_service.controller;
import com.ecommerce_backend.inventory_service.dto.*;
import com.ecommerce_backend.inventory_service.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
 private final InventoryService service;
 public InventoryController(InventoryService service){this.service=service;}
 @GetMapping("/products/{productId}") public InventoryResponse get(@PathVariable Long productId){return service.getByProductId(productId);}
 @PostMapping("/consume") @ResponseStatus(HttpStatus.NO_CONTENT)
 public void consume(@RequestBody ConsumeStockRequest request){service.consume(request);}
}
