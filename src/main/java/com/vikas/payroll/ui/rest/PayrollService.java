package com.vikas.payroll.ui.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: using block() recommended in production systems. It's recommended to use reactive programming instead.
// TODO: use flux for list ?
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

    public Employee createEmployee(Employee newEmployee) {
        return webClient.post()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newEmployee)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        return webClient.put()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedEmployee)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }

    public void deleteEmployee(Long employeeId) {
        webClient.delete()
                .uri("/employees/{id}", employeeId)
                .exchange()
                .block();
    }

    public BankDetails getBankDetails(Long employeeId) {
        return webClient.get()
                .uri("/employees/{id}/bank_details", employeeId)
                .retrieve()
                .bodyToMono(BankDetails.class)
                .block();
    }

    public BankDetails createBankDetails(Long employeeId, BankDetails bankDetails) {
        return webClient.post()
                .uri("/employees/{employeeId}/bank_details", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bankDetails)
                .retrieve()
                .bodyToMono(BankDetails.class)
                .block();
    }

    public BankDetails updateBankDetails(Long employeeId, BankDetails bankDetails) {
        return webClient.put()
                .uri("/employees/{employeeId}/bank_details", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bankDetails)
                .retrieve()
                .bodyToMono(BankDetails.class)
                .block();
    }

    public void deleteBankDetails(Long employeeId) {
        webClient.delete()
                .uri("/employees/{id}/bank_details", employeeId)
                .exchange()
                .block();
    }

    // ======================================== ENUM Endpoints =========================================================
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

    public List<String> getAllDesignations() {
        return webClient.get()
                .uri("/enums/designations")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

    public List<String> getAllTypes() {
        return webClient.get()
                .uri("/enums/types")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

    public List<String> getAllStatus() {
        return webClient.get()
                .uri("/enums/status")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
                }).block();
    }

}
