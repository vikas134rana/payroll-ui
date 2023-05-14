package com.vikas.payroll.ui.rest;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "bankName",
        "bankBranch",
        "ifscCode",
        "accountNumber"
})
@Generated("jsonschema2pojo")
public class BankDetails {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("bankName")
    private String bankName;
    @JsonProperty("bankBranch")
    private String bankBranch;
    @JsonProperty("ifscCode")
    private String ifscCode;
    @JsonProperty("accountNumber")
    private String accountNumber;
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

    @JsonProperty("bankName")
    public String getBankName() {
        return bankName;
    }

    @JsonProperty("bankName")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @JsonProperty("bankBranch")
    public String getBankBranch() {
        return bankBranch;
    }

    @JsonProperty("bankBranch")
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    @JsonProperty("ifscCode")
    public String getIfscCode() {
        return ifscCode;
    }

    @JsonProperty("ifscCode")
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    @JsonProperty("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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