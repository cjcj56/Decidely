package kisch.binyamin.decidely.factor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kisch.binyamin.decidely.model.Factor;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Long> {

	public List<Factor> findByOptionId(Long optionId);
	
	public boolean existsByIdAndOptionId(Long factorId, Long optionId);
	
	@Query("SELECT f FROM FACTORS f WHERE f.option.id=:optionId AND f.option.decision.id=:decisionId")
	public List<Factor> findByDecisionIdAndOptionId(@Param("decisionId") Long decisionId, @Param("optionId") Long optionId);

	@Query("SELECT f FROM FACTORS f WHERE f.id =:factorId AND f.option.id=:optionId AND f.option.decision.id=:decisionId")
	public boolean existsByIdAndOptionIdAndDecisionId(@Param("factorId") Long factorId, @Param("optionId") Long optionId, @Param("decisionId") Long decisionId);


}
