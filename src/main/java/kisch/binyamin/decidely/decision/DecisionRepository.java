package kisch.binyamin.decidely.decision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kisch.binyamin.decidely.model.Decision;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {

}
