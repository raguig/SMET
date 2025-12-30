package com.example.demo.touristinfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformationTouristiqueDTO {

    private Long id;
    private String nom;
    private String description;
    private String localisation;
    private String type;

    // Type-specific fields
    private String periode;        // Monument
    private Double prixParNuit;    // Hotel
    private String menuExemple;    // Restaurant
}