package kisch.binyamin.decidely.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kisch.binyamin.decidely.model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
	public List<Option> findByDecisionId(Long decisionId);
}
