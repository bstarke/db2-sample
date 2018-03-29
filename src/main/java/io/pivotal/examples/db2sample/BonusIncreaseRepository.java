package io.pivotal.examples.db2sample;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BonusIncreaseRepository {

    List<EmployeeBonus> bonusIncrease(@Param("bonusFactor") Double bonusFactor, @Param("bonusMaxSumForDept") Double bonusMaxSumForDept);

}
