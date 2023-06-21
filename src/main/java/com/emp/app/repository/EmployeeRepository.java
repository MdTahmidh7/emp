package com.emp.app.repository;

import com.emp.app.domain.Employee;
import com.emp.app.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {



      /*  "lower(e.empName) LIKE CONCAT('%', lower(:query), '%') OR " +
        "lower(e.empMobile) LIKE CONCAT('%', lower(:query), '%')")*/
    @Query("SELECT e FROM Employee e " +
        "WHERE lower(e.empName) like concat('%', lower(:query), '%') ")
    Page<Employee> searchEmployee(String query, Pageable pageable);



}




