package io.pivotal.examples.db2sample;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class BonusIncreaseRepositoryImpl implements BonusIncreaseRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EmployeeBonus> bonusIncrease(Double bonusFactor, Double bonusMaxSumForDept) {
        StoredProcedureQuery bonusIncrease = em.createNamedStoredProcedureQuery("bonusIncrease");
        bonusIncrease.setParameter("p_bonusFactor", bonusFactor);
        bonusIncrease.setParameter("p_bonusMaxSumForDept", bonusMaxSumForDept);
        bonusIncrease.execute();
        System.out.println(bonusIncrease.getOutputParameterValue("p_deptsWithoutNewBonuses"));
        System.out.println(bonusIncrease.getOutputParameterValue("p_countDeptsViewed"));
        System.out.println(bonusIncrease.getOutputParameterValue("p_countDeptsBonusChanged"));
        System.out.println(bonusIncrease.getOutputParameterValue("p_errorMsg"));
        return bonusIncrease.getResultList();
    }
}
