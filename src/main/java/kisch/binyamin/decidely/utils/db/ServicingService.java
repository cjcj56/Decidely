package kisch.binyamin.decidely.utils.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.decision.DecisionService;
import kisch.binyamin.decidely.factor.FactorService;
import kisch.binyamin.decidely.option.OptionService;
import kisch.binyamin.decidely.score.ScoreService;

@Service
public class ServicingService {

	@Autowired
	private DecisionService decisionService;
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
	private FactorService factorService;
	
	@Autowired
	private ScoreService scoreService;

	public DecisionService getDecisionService() {
		return decisionService;
	}

	public void setDecisionService(DecisionService decisionService) {
		this.decisionService = decisionService;
	}

	public OptionService getOptionService() {
		return optionService;
	}

	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}

	public FactorService getFactorService() {
		return factorService;
	}

	public void setFactorService(FactorService factorService) {
		this.factorService = factorService;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
}
