package io.pivotal.examples.db2sample;


import org.springframework.data.repository.CrudRepository;

public interface EmployeeBonusRepository extends CrudRepository<EmployeeBonus, Long>, BonusIncreaseRepository {

}
