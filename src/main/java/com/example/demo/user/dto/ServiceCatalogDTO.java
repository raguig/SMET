package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCatalogDTO {
    private String name;
    private String description;
    private String baseUrl;
    private List<EndpointInfo> endpoints;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EndpointInfo {
        private String path;
        private String method;
        private String description;
        private Boolean requiresAuth;
    }
}

