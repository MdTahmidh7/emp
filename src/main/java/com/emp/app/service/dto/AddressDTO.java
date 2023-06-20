package com.emp.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.emp.app.domain.Address} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddressDTO implements Serializable {

    private Long id;

    private Integer houseNo;

    private Integer roadNo;

    private String block;

    private Integer section;

    private String division;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Integer getRoadNo() {
        return roadNo;
    }

    public void setRoadNo(Integer roadNo) {
        this.roadNo = roadNo;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        AddressDTO addressDTO = (AddressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, addressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", houseNo=" + getHouseNo() +
            ", roadNo=" + getRoadNo() +
            ", block='" + getBlock() + "'" +
            ", section=" + getSection() +
            ", division='" + getDivision() + "'" +
            "}";
    }
}
