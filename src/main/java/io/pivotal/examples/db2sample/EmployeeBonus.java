package io.pivotal.examples.db2sample;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "bonusIncrease",
                procedureName = "bonus_increase",
                resultClasses = { EmployeeBonus.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "p_bonusFactor",
                                type = Double.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "p_bonusMaxSumForDept",
                                type = Double.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "p_deptsWithoutNewBonuses",
                                type = String.class,
                                mode = ParameterMode.OUT),
                        @StoredProcedureParameter(
                                name = "p_countDeptsViewed",
                                type = Integer.class,
                                mode = ParameterMode.OUT),
                        @StoredProcedureParameter(
                                name = "p_countDeptsBonusChanged",
                                type = Integer.class,
                                mode = ParameterMode.OUT),
                        @StoredProcedureParameter(
                                name = "p_errorMsg",
                                type = String.class,
                                mode = ParameterMode.OUT)
                })
})
public class EmployeeBonus {
    @Id
    @Column(name = "EMPNO")
    private Long employeeNumber;
    @Column(name = "WORKDEPT")
    private String workDepartment;
    @Column(name = "BONUS")
    private String bonus;
}
