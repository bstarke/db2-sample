package io.pivotal.examples.db2sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastNameIgnoreCase(@Param("name")String lastName);

    List<Employee> findByPositionIgnoreCase(@Param("position")String position);

    List<Employee> findBySalaryGreaterThan(@Param("salary")Double salary);
}
