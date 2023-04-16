package com.vikas.payroll.ui.rest;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"gender",
"dateOfBirth",
"maritalStatus",
"address",
"contactNumber",
"email",
"department",
"designation",
"type",
"status",
"joiningDate",
"lastWorkingDay"
})
@Generated("jsonschema2pojo")
public class Employee {

@JsonProperty("id")
private Long id;
@JsonProperty("name")
private String name;
@JsonProperty("gender")
private String gender;
@JsonProperty("dateOfBirth")
private LocalDate dateOfBirth;
@JsonProperty("maritalStatus")
private String maritalStatus;
@JsonProperty("address")
private String address;
@JsonProperty("contactNumber")
private String contactNumber;
@JsonProperty("email")
private String email;
@JsonProperty("department")
private String department;
@JsonProperty("designation")
private String designation;
@JsonProperty("type")
private String type;
@JsonProperty("status")
private String status;
@JsonProperty("joiningDate")
private LocalDate joiningDate;
@JsonProperty("lastWorkingDay")
private LocalDate lastWorkingDay;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public Long getId() {
return id;
}

@JsonProperty("id")
public void setId(Long id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("gender")
public String getGender() {
return gender;
}

@JsonProperty("gender")
public void setGender(String gender) {
this.gender = gender;
}

@JsonProperty("dateOfBirth")
public LocalDate getDateOfBirth() {
return dateOfBirth;
}

@JsonProperty("dateOfBirth")
public void setDateOfBirth(LocalDate dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}

@JsonProperty("maritalStatus")
public String getMaritalStatus() {
return maritalStatus;
}

@JsonProperty("maritalStatus")
public void setMaritalStatus(String maritalStatus) {
this.maritalStatus = maritalStatus;
}

@JsonProperty("address")
public String getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(String address) {
this.address = address;
}

@JsonProperty("contactNumber")
public String getContactNumber() {
return contactNumber;
}

@JsonProperty("contactNumber")
public void setContactNumber(String contactNumber) {
this.contactNumber = contactNumber;
}

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("department")
public String getDepartment() {
return department;
}

@JsonProperty("department")
public void setDepartment(String department) {
this.department = department;
}

@JsonProperty("designation")
public String getDesignation() {
return designation;
}

@JsonProperty("designation")
public void setDesignation(String designation) {
this.designation = designation;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("joiningDate")
public LocalDate getJoiningDate() {
return joiningDate;
}

@JsonProperty("joiningDate")
public void setJoiningDate(LocalDate joiningDate) {
this.joiningDate = joiningDate;
}

@JsonProperty("lastWorkingDay")
public Object getLastWorkingDay() {
return lastWorkingDay;
}

@JsonProperty("lastWorkingDay")
public void setLastWorkingDay(LocalDate lastWorkingDay) {
this.lastWorkingDay = lastWorkingDay;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}