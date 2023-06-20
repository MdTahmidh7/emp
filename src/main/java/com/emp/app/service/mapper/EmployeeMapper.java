package com.emp.app.service.mapper;

import com.emp.app.domain.Address;
import com.emp.app.domain.Employee;
import com.emp.app.service.dto.AddressDTO;
import com.emp.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "employeeAddress", source = "employeeAddress", qualifiedByName = "addressId")
    EmployeeDTO toDto(Employee s);

    @Named("addressId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AddressDTO toDtoAddressId(Address address);
}
