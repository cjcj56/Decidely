package kisch.binyamin.decidely.score;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.ModelEntityService;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.model.Score;
import kisch.binyamin.decidely.utils.db.DbMessagesConsts;

@Service
public class ScoreService extends ModelEntityService<Score, ScoreRepository>{

	public Map<String, Score> findByDecisionId(Long decisionId) {
		return getServicingService().getDecisionService().getOne(decisionId).getScores();
	}

	public boolean existsByIdAndDecisionId(Long scoreId, Long decisionId) {
		return findByIdAndDecisionId(scoreId, decisionId) == null;
	}

	public boolean existsByIdAndOptionIdAndFactorId(Long scoreId, Long optionId, Long factorId) {
		return getRepository().existsByIdAndOptionIdAndFactorId(scoreId, optionId, factorId);
	}

	public Score findByIdAndDecisionId(Long scoreId, Long decisionId) {
		Decision decision = getServicingService().getDecisionService().getOne(decisionId);
		if (decision == null) {
			return null;
		}
		Score score = getOne(scoreId);
		if (score == null) {
			return null;
		}
		if (decision.getScores().containsKey(score.generateMapKey())) {
			return scoreId.equals(decision.getScores().get(score.generateMapKey()).getId()) ? score : null;
		} else {
			return null;
		}
	}

	public void saveToOptionIdAndFactorId(Score score, Long optionId, Long factorId) {
		Option option = getServicingService().getOptionService().getOne(optionId);
		if(option == null) {
			throw new EntityNotFoundException(String.format(DbMessagesConsts.ENTITY_NOT_FOUND_BY_ID, "Option", optionId));
		}
		Factor factor = getServicingService().getFactorService().getOne(factorId);
		if(factor == null) {
			throw new EntityNotFoundException(String.format(DbMessagesConsts.ENTITY_NOT_FOUND_BY_ID, "Factor", factorId));
		}		
		score.setOption(option);
		score.setFactor(factor);
		saveAndFlush(score);
	}
	
	public void saveAllToDecisionId(List<Score> scores, Long decisionId) {
		Decision decision = getServicingService().getDecisionService().getOne(decisionId);
		if(decision == null) {
			throw new EntityNotFoundException(String.format(DbMessagesConsts.ENTITY_NOT_FOUND_BY_ID, "Decision", decisionId));
		}
		saveAll(scores);
		flush();
		decision.setScores(scores.stream().collect(Collectors.toMap(s -> s.getOption().getId() + ":" + s.getFactor().getId(), Function.identity())));
		getServicingService().getDecisionService().saveAndFlush(decision);
	}

}
