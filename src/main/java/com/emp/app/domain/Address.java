package com.emp.app.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "house_no")
    private Integer houseNo;

    @Column(name = "road_no")
    private Integer roadNo;

    @Column(name = "block")
    private String block;

    @Column(name = "section")
    private Integer section;

    @Column(name = "division")
    private String division;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Address id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHouseNo() {
        return this.houseNo;
    }

    public Address houseNo(Integer houseNo) {
        this.setHouseNo(houseNo);
        return this;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Integer getRoadNo() {
        return this.roadNo;
    }

    public Address roadNo(Integer roadNo) {
        this.setRoadNo(roadNo);
        return this;
    }

    public void setRoadNo(Integer roadNo) {
        this.roadNo = roadNo;
    }

    public String getBlock() {
        return this.block;
    }

    public Address block(String block) {
        this.setBlock(block);
        return this;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Integer getSection() {
        return this.section;
    }

    public Address section(Integer section) {
        this.setSection(section);
        return this;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getDivision() {
        return this.division;
    }

    public Address division(String division) {
        this.setDivision(division);
        return this;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", houseNo=" + getHouseNo() +
            ", roadNo=" + getRoadNo() +
            ", block='" + getBlock() + "'" +
            ", section=" + getSection() +
            ", division='" + getDivision() + "'" +
            "}";
    }
}
