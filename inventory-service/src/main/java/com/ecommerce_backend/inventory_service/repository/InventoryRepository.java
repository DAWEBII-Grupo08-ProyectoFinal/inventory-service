package com.ecommerce_backend.inventory_service.repository;
import com.ecommerce_backend.inventory_service.entity.InventoryItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface InventoryRepository extends JpaRepository<InventoryItem,Long> {
 Optional<InventoryItem> findByProductId(Long productId);
 @Lock(LockModeType.PESSIMISTIC_WRITE)
 @Query("select i from InventoryItem i where i.productId=:productId")
 Optional<InventoryItem> findByProductIdForUpdate(@Param("productId") Long productId);
}
