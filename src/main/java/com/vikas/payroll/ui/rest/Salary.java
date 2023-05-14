package com.vikas.payroll.ui.rest;

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
"ctc",
"basicSalary",
"hra",
"specialAllowance"
})
@Generated("jsonschema2pojo")
public class Salary {

@JsonProperty("id")
private Integer id;
@JsonProperty("ctc")
private Double ctc;
@JsonProperty("basicSalary")
private Double basicSalary;
@JsonProperty("hra")
private Double hra;
@JsonProperty("specialAllowance")
private Double specialAllowance;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("ctc")
public Double getCtc() {
return ctc;
}

@JsonProperty("ctc")
public void setCtc(Double ctc) {
this.ctc = ctc;
}

@JsonProperty("basicSalary")
public Double getBasicSalary() {
return basicSalary;
}

@JsonProperty("basicSalary")
public void setBasicSalary(Double basicSalary) {
this.basicSalary = basicSalary;
}

@JsonProperty("hra")
public Double getHra() {
return hra;
}

@JsonProperty("hra")
public void setHra(Double hra) {
this.hra = hra;
}

@JsonProperty("specialAllowance")
public Double getSpecialAllowance() {
return specialAllowance;
}

@JsonProperty("specialAllowance")
public void setSpecialAllowance(Double specialAllowance) {
this.specialAllowance = specialAllowance;
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