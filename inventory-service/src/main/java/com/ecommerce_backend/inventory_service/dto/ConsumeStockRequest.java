package com.ecommerce_backend.inventory_service.dto;
import java.util.List;
public record ConsumeStockRequest(List<StockItemRequest> items) {}
