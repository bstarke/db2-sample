package io.pivotal.examples.db2sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastNameIgnoreCase(@Param("name")String lastName);

    List<Employee> findByPositionIgnoreCase(@Param("position")String position);

    List<Employee> findBySalaryGreaterThan(@Param("salary")Double salary);
}
