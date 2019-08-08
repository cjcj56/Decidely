package kisch.binyamin.decidely.decision;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityService;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.model.Score;

@Service
public class DecisionService extends ModelEntityService<Decision, DecisionRepository>{

	public void deleteAllScores(Long decisionId) {
		Collection<Score> decisionScores = getOne(decisionId).getScores().values();
		getServicingService().getScoreService().deleteAll(decisionScores);
		getServicingService().getScoreService().flush();
	}

	public void saveAllDecisionOptions(List<Option> options) {
		getServicingService().getOptionService().saveAll(options);
		getServicingService().getOptionService().flush();
	}

	
}
