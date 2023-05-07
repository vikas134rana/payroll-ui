package com.vikas.payroll.ui.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private final WebClient webClient;

    public PayrollService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080").build();
    }

    public List<Employee> getEmployees() {
        Employee[] employeesArray = webClient.get()
                .uri("/employees")
                .retrieve().bodyToMono(Employee[].class).block();
        return Arrays.stream(employeesArray != null ? employeesArray : new Employee[0]).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesContainingName(String name) {
        Employee[] employeesArray = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/employees/search").queryParam("name", name).build())
                .retrieve().bodyToMono(Employee[].class).block();
        return Arrays.stream(employeesArray != null ? employeesArray : new Employee[0]).collect(Collectors.toList());
    }

    public List<String> getAllDepartments() {
        return webClient.get()
                .uri("/enums/departments")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

    public List<String> getAllGenders() {
        return webClient.get()
                .uri("/enums/genders")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

    public List<String> getAllMaritalStatus() {
        return webClient.get()
                .uri("/enums/marital_status")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

    /* public void saveEmployees() {
        webClient.post().uri("/employees").retrieve().bodyToMono(Employee[].class).block();
        return Arrays.stream(employeesArray != null ? employeesArray : new Employee[0]).collect(Collectors.toList());
    }*/

}
