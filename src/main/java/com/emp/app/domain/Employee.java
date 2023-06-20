package com.emp.app.domain;

import com.emp.app.domain.enumeration.gender;
import com.emp.app.domain.enumeration.maritalStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_age")
    private Float empAge;

    @Column(name = "emp_mobile")
    private String empMobile;

    @Column(name = "emp_birth_date")
    private LocalDate empBirthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "emp_gender")
    private gender empGender;

    @Column(name = "emp_personal_business")
    private Boolean empPersonalBusiness;

    @Enumerated(EnumType.STRING)
    @Column(name = "emp_marital_status")
    private maritalStatus empMaritalStatus;

    @Column(name = "emp_experience")
    private Duration empExperience;

    @Column(name = "emp_nationality")
    private String empNationality;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address employeeAddress;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpName() {
        return this.empName;
    }

    public Employee empName(String empName) {
        this.setEmpName(empName);
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Float getEmpAge() {
        return this.empAge;
    }

    public Employee empAge(Float empAge) {
        this.setEmpAge(empAge);
        return this;
    }

    public void setEmpAge(Float empAge) {
        this.empAge = empAge;
    }

    public String getEmpMobile() {
        return this.empMobile;
    }

    public Employee empMobile(String empMobile) {
        this.setEmpMobile(empMobile);
        return this;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public LocalDate getEmpBirthDate() {
        return this.empBirthDate;
    }

    public Employee empBirthDate(LocalDate empBirthDate) {
        this.setEmpBirthDate(empBirthDate);
        return this;
    }

    public void setEmpBirthDate(LocalDate empBirthDate) {
        this.empBirthDate = empBirthDate;
    }

    public gender getEmpGender() {
        return this.empGender;
    }

    public Employee empGender(gender empGender) {
        this.setEmpGender(empGender);
        return this;
    }

    public void setEmpGender(gender empGender) {
        this.empGender = empGender;
    }

    public Boolean getEmpPersonalBusiness() {
        return this.empPersonalBusiness;
    }

    public Employee empPersonalBusiness(Boolean empPersonalBusiness) {
        this.setEmpPersonalBusiness(empPersonalBusiness);
        return this;
    }

    public void setEmpPersonalBusiness(Boolean empPersonalBusiness) {
        this.empPersonalBusiness = empPersonalBusiness;
    }

    public maritalStatus getEmpMaritalStatus() {
        return this.empMaritalStatus;
    }

    public Employee empMaritalStatus(maritalStatus empMaritalStatus) {
        this.setEmpMaritalStatus(empMaritalStatus);
        return this;
    }

    public void setEmpMaritalStatus(maritalStatus empMaritalStatus) {
        this.empMaritalStatus = empMaritalStatus;
    }

    public Duration getEmpExperience() {
        return this.empExperience;
    }

    public Employee empExperience(Duration empExperience) {
        this.setEmpExperience(empExperience);
        return this;
    }

    public void setEmpExperience(Duration empExperience) {
        this.empExperience = empExperience;
    }

    public String getEmpNationality() {
        return this.empNationality;
    }

    public Employee empNationality(String empNationality) {
        this.setEmpNationality(empNationality);
        return this;
    }

    public void setEmpNationality(String empNationality) {
        this.empNationality = empNationality;
    }

    public Address getEmployeeAddress() {
        return this.employeeAddress;
    }

    public void setEmployeeAddress(Address address) {
        this.employeeAddress = address;
    }

    public Employee employeeAddress(Address address) {
        this.setEmployeeAddress(address);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
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
            "}";
    }
}
