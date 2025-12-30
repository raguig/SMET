package com.example.demo.touristinfo.service;

import com.example.demo.touristinfo.dto.InformationTouristiqueDTO;
import com.example.demo.touristinfo.mapper.InformationTouristiqueMapper;
import com.example.demo.touristinfo.model.InformationTouristique;
import com.example.demo.touristinfo.persistence.InformationTouristiqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InformationTouristiqueService {

    private final InformationTouristiqueRepository repository;
    private final InformationTouristiqueMapper mapper;

    public List<InformationTouristiqueDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public InformationTouristiqueDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Touristic information not found with id: " + id));
    }

    @Transactional
    public InformationTouristiqueDTO create(InformationTouristiqueDTO dto) {
        InformationTouristique entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public InformationTouristiqueDTO update(Long id, InformationTouristiqueDTO dto) {
        InformationTouristique existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found with id: " + id));

        existing.setNom(dto.getNom());
        existing.setDescription(dto.getDescription());
        existing.setLocalisation(dto.getLocalisation());

        existing = repository.save(existing);
        return mapper.toDto(existing);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}