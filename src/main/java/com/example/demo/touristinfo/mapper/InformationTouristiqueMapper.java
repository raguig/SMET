package com.example.demo.touristinfo.mapper;

import com.example.demo.touristinfo.dto.InformationTouristiqueDTO;
import com.example.demo.touristinfo.model.*;
import org.springframework.stereotype.Component;

@Component
public class InformationTouristiqueMapper {

    public InformationTouristiqueDTO toDto(InformationTouristique entity) {
        if (entity == null) return null;

        InformationTouristiqueDTO dto = InformationTouristiqueDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .description(entity.getDescription())
                .localisation(entity.getLocalisation())
                .type(entity.getType())
                .build();

        if (entity instanceof Monument monument) {
            dto.setPeriode(monument.getPeriode());
        } else if (entity instanceof Hotel hotel) {
            dto.setPrixParNuit(hotel.getPrixParNuit());
        } else if (entity instanceof Restaurant restaurant) {
            dto.setMenuExemple(restaurant.getMenuExemple());
        }

        return dto;
    }

    public InformationTouristique toEntity(InformationTouristiqueDTO dto) {
        if (dto == null || dto.getType() == null) {
            throw new IllegalArgumentException("Type is required");
        }

        InformationTouristique entity = switch (dto.getType()) {
            case "Monument" -> {
                Monument m = new Monument();
                m.setPeriode(dto.getPeriode());
                yield m;
            }
            case "Hotel" -> {
                Hotel h = new Hotel();
                h.setPrixParNuit(dto.getPrixParNuit());
                yield h;
            }
            case "Restaurant" -> {
                Restaurant r = new Restaurant();
                r.setMenuExemple(dto.getMenuExemple());
                yield r;
            }
            default -> throw new IllegalArgumentException("Unknown type: " + dto.getType());
        };

        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setLocalisation(dto.getLocalisation());

        return entity;
    }
}