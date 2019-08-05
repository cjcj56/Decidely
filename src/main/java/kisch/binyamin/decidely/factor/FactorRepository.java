package kisch.binyamin.decidely.factor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kisch.binyamin.decidely.model.Factor;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Long> {

	public List<Factor> findByDecisionId(Long decisionId);
	
}
