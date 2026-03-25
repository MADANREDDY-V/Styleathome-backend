package com.styleathome.controller;

import com.styleathome.entity.ServiceOffering;
import com.styleathome.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping
    public ResponseEntity<List<ServiceOffering>> getAllServices() {
        return ResponseEntity.ok(serviceOfferingService.getAllServiceOfferings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceOfferingService.getServiceOfferingById(id));
    }
}
