package com.ecommerce_backend.inventory_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
 public Map<String,String> notFound(IllegalArgumentException e){return Map.of("error",e.getMessage());}
 @ExceptionHandler(IllegalStateException.class) @ResponseStatus(HttpStatus.CONFLICT)
 public Map<String,String> conflict(IllegalStateException e){return Map.of("error",e.getMessage());}
}
