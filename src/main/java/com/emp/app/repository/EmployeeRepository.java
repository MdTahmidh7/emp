package com.emp.app.repository;

import com.emp.app.domain.Employee;
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

    @Query("SELECT e FROM Employee e WHERE " +
        "lower(e.empName) = lower(:query) ")

      /*  "lower(e.empName) LIKE CONCAT('%', lower(:query), '%') OR " +
        "lower(e.empMobile) LIKE CONCAT('%', lower(:query), '%')")*/

    Page<Employee> searchEmployee(@Param("query") String query, Pageable pageable);

}




