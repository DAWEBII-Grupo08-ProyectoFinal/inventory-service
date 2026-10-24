package com.ecommerce_backend.inventory_service.entity;
import jakarta.persistence.*;
@Entity
@Table(name="inventory_items", uniqueConstraints=@UniqueConstraint(name="uk_inventory_product_id", columnNames="product_id"))
public class InventoryItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="product_id", nullable=false) private Long productId;
 @Column(name="current_quantity", nullable=false) private Integer currentQuantity;
 protected InventoryItem() {}
 public InventoryItem(Long productId, Integer currentQuantity) { this.productId=productId; this.currentQuantity=currentQuantity; }
 public Long getId(){return id;} public Long getProductId(){return productId;} public Integer getCurrentQuantity(){return currentQuantity;}
 public void setCurrentQuantity(Integer quantity){this.currentQuantity=quantity;}
}
