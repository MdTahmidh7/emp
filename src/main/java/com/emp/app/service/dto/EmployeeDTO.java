package com.emp.app.service.dto;

import com.emp.app.domain.enumeration.gender;
import com.emp.app.domain.enumeration.maritalStatus;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.emp.app.domain.Employee} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmployeeDTO implements Serializable {

    private Long id;

    private String empName;

    private Float empAge;

    private String empMobile;

    private LocalDate empBirthDate;

    private gender empGender;

    private Boolean empPersonalBusiness;

    private maritalStatus empMaritalStatus;

    private Duration empExperience;

    private String empNationality;

    private AddressDTO employeeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Float getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Float empAge) {
        this.empAge = empAge;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public LocalDate getEmpBirthDate() {
        return empBirthDate;
    }

    public void setEmpBirthDate(LocalDate empBirthDate) {
        this.empBirthDate = empBirthDate;
    }

    public gender getEmpGender() {
        return empGender;
    }

    public void setEmpGender(gender empGender) {
        this.empGender = empGender;
    }

    public Boolean getEmpPersonalBusiness() {
        return empPersonalBusiness;
    }

    public void setEmpPersonalBusiness(Boolean empPersonalBusiness) {
        this.empPersonalBusiness = empPersonalBusiness;
    }

    public maritalStatus getEmpMaritalStatus() {
        return empMaritalStatus;
    }

    public void setEmpMaritalStatus(maritalStatus empMaritalStatus) {
        this.empMaritalStatus = empMaritalStatus;
    }

    public Duration getEmpExperience() {
        return empExperience;
    }

    public void setEmpExperience(Duration empExperience) {
        this.empExperience = empExperience;
    }

    public String getEmpNationality() {
        return empNationality;
    }

    public void setEmpNationality(String empNationality) {
        this.empNationality = empNationality;
    }

    public AddressDTO getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(AddressDTO employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", empName='" + getEmpName() + "'" +
            ", empAge=" + getEmpAge() +
            ", empMobile='" + getEmpMobile() + "'" +
            ", empBirthDate='" + getEmpBirthDate() + "'" +
            ", empGender='" + getEmpGender() + "'" +
            ", empPersonalBusiness='" + getEmpPersonalBusiness() + "'" +
            ", empMaritalStatus='" + getEmpMaritalStatus() + "'" +
            ", empExperience='" + getEmpExperience() + "'" +
            ", empNationality='" + getEmpNationality() + "'" +
            ", employeeAddress=" + getEmployeeAddress() +
            "}";
    }
}
