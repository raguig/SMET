package com.example.demo.touristinfo.persistence;

import com.example.demo.touristinfo.model.InformationTouristique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationTouristiqueRepository extends JpaRepository<InformationTouristique, Long> {
    List<InformationTouristique> findByLocalisationContainingIgnoreCase(String localisation);
}