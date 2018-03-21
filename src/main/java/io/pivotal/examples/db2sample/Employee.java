package io.pivotal.examples.db2sample;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Employee {
    @Id
    @Column(name = "EMPNO")
    private Long employeeNumber;
    @Column(name = "FIRSTNME")
    private String firstName;
    @Column(name = "MIDINIT")
    private String middleIntial;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "JOB")
    private String position;
    @Column(name = "SEX")
    private String gender;
    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;
    @Column(name = "HIREDATE")
    private LocalDate hireDate;
    @Column(name = "SALARY")
    private Double salary;
}
