package com.vikas.payroll.ui.rest;

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
        Employee[] employeesArray = webClient.get().uri("/employees").retrieve().bodyToMono(Employee[].class).block();
        return Arrays.stream(employeesArray != null ? employeesArray : new Employee[0]).collect(Collectors.toList());
    }


}
