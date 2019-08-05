package kisch.binyamin.decidely.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kisch.binyamin.decidely.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

	public boolean existsByIdAndOptionIdAndFactorId(Long scoreId, Long optionId, Long factorId);
	
}
