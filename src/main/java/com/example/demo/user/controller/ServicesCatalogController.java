package com.example.demo.user.controller;

import com.example.demo.user.dto.ServiceCatalogDTO;
import com.example.demo.user.service.ServicesCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServicesCatalogController {

    private final ServicesCatalogService servicesCatalogService;

    @GetMapping
    public ResponseEntity<List<ServiceCatalogDTO>> getAllServices() {
        List<ServiceCatalogDTO> services = servicesCatalogService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ServiceCatalogDTO> getServiceByName(@PathVariable String name) {
        ServiceCatalogDTO service = servicesCatalogService.getServiceByName(name);
        return ResponseEntity.ok(service);
    }
}

