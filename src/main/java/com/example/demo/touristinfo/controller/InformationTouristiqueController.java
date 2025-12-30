package com.example.demo.touristinfo.controller;

import com.example.demo.touristinfo.dto.InformationTouristiqueDTO;
import com.example.demo.touristinfo.service.InformationTouristiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/information-touristique")
@RequiredArgsConstructor
public class InformationTouristiqueController {

    private final InformationTouristiqueService service;

    @GetMapping
    public List<InformationTouristiqueDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformationTouristiqueDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InformationTouristiqueDTO> create(@RequestBody InformationTouristiqueDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationTouristiqueDTO> update(@PathVariable Long id, @RequestBody InformationTouristiqueDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}