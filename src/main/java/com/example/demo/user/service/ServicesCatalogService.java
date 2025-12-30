package com.example.demo.user.service;

import com.example.demo.user.dto.ServiceCatalogDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicesCatalogService {

    private final Map<String, ServiceCatalogDTO> servicesCatalog;

    public ServicesCatalogService() {
        this.servicesCatalog = initializeCatalog();
    }

    public List<ServiceCatalogDTO> getAllServices() {
        return servicesCatalog.values().stream().toList();
    }

    public ServiceCatalogDTO getServiceByName(String name) {
        ServiceCatalogDTO service = servicesCatalog.get(name.toLowerCase());
        if (service == null) {
            throw new RuntimeException("Service not found with name: " + name);
        }
        return service;
    }

    private Map<String, ServiceCatalogDTO> initializeCatalog() {
        Map<String, ServiceCatalogDTO> catalog = new HashMap<>();

        // User Management Service
        ServiceCatalogDTO userService = ServiceCatalogDTO.builder()
                .name("User Management")
                .description("Manage user accounts, profiles, and user-related operations")
                .baseUrl("/api/users")
                .endpoints(Arrays.asList(
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users")
                                .method("GET")
                                .description("Get all users")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users/{id}")
                                .method("GET")
                                .description("Get user by ID")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users/me")
                                .method("GET")
                                .description("Get current authenticated user")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users")
                                .method("POST")
                                .description("Create a new user")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users/{id}")
                                .method("PUT")
                                .description("Update user by ID")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/users/{id}")
                                .method("DELETE")
                                .description("Delete user by ID")
                                .requiresAuth(true)
                                .build()
                ))
                .build();
        catalog.put("user management", userService);
        catalog.put("users", userService);

        // Authentication Service
        ServiceCatalogDTO authService = ServiceCatalogDTO.builder()
                .name("Authentication")
                .description("User authentication and JWT token generation")
                .baseUrl("/api/auth")
                .endpoints(Arrays.asList(
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/auth/login")
                                .method("POST")
                                .description("Login and get JWT token")
                                .requiresAuth(false)
                                .build()
                ))
                .build();
        catalog.put("authentication", authService);
        catalog.put("auth", authService);
        catalog.put("login", authService);

        // Tourist Information Service
        ServiceCatalogDTO touristInfoService = ServiceCatalogDTO.builder()
                .name("Tourist Information")
                .description("Manage tourist information including hotels, monuments, and restaurants")
                .baseUrl("/api/information-touristique")
                .endpoints(Arrays.asList(
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/information-touristique")
                                .method("GET")
                                .description("Get all tourist information")
                                .requiresAuth(false)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/information-touristique/{id}")
                                .method("GET")
                                .description("Get tourist information by ID")
                                .requiresAuth(false)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/information-touristique")
                                .method("POST")
                                .description("Create new tourist information")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/information-touristique/{id}")
                                .method("PUT")
                                .description("Update tourist information by ID")
                                .requiresAuth(true)
                                .build(),
                        ServiceCatalogDTO.EndpointInfo.builder()
                                .path("/api/information-touristique/{id}")
                                .method("DELETE")
                                .description("Delete tourist information by ID")
                                .requiresAuth(true)
                                .build()
                ))
                .build();
        catalog.put("tourist information", touristInfoService);
        catalog.put("touristinfo", touristInfoService);
        catalog.put("information-touristique", touristInfoService);

        return catalog;
    }
}

