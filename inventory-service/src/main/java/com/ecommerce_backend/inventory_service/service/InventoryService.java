package com.ecommerce_backend.inventory_service.service;
import com.ecommerce_backend.inventory_service.dto.*;
import com.ecommerce_backend.inventory_service.entity.InventoryItem;
import com.ecommerce_backend.inventory_service.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class InventoryService {
 private final InventoryRepository repository;
 public InventoryService(InventoryRepository repository){this.repository=repository;}
 public InventoryResponse getByProductId(Long productId){
  return repository.findByProductId(productId).map(this::toResponse)
   .orElseThrow(()->new IllegalArgumentException("No existe inventario para el producto "+productId));
 }
 @Transactional
 public void consume(ConsumeStockRequest request){
  if(request==null||request.items()==null||request.items().isEmpty()) throw new IllegalArgumentException("La lista de productos está vacía");
  Map<Long,Integer> quantities=new LinkedHashMap<>();
  for(StockItemRequest item:request.items()){
   if(item.productId()==null||item.quantity()==null||item.quantity()<=0) throw new IllegalArgumentException("Cada producto debe tener ID y cantidad mayor que cero");
   quantities.merge(item.productId(),item.quantity(),Integer::sum);
  }
  Map<Long,InventoryItem> found=new LinkedHashMap<>();
  for(var entry:quantities.entrySet()){
   InventoryItem stock=repository.findByProductIdForUpdate(entry.getKey())
    .orElseThrow(()->new IllegalArgumentException("No existe inventario para el producto "+entry.getKey()));
   if(stock.getCurrentQuantity()<entry.getValue()) throw new IllegalStateException("Stock insuficiente para el producto "+entry.getKey());
   found.put(entry.getKey(),stock);
  }
  for(var entry:found.entrySet()) entry.getValue().setCurrentQuantity(entry.getValue().getCurrentQuantity()-quantities.get(entry.getKey()));
  repository.saveAll(found.values());
 }
 private InventoryResponse toResponse(InventoryItem i){return new InventoryResponse(i.getId(),i.getProductId(),i.getCurrentQuantity());}
}
