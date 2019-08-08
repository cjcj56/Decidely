package kisch.binyamin.decidely.decision;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityController;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.result.Result;

@RestController
public class DecisionController extends ModelEntityController<Decision, DecisionService> {
	
	@RequestMapping("/decisions")
	public List<Decision> findAllDecisions() {
		return getService().findAll();
	}
	
	@RequestMapping("/decisions/{id:\\d+}")
	public Decision getDecision(@PathVariable Long id) {
		return getService().getOne(id);
	}
	
	@RequestMapping("/decisions/{id:\\d+}/results")
	public List<Option> getDecisionResults(@PathVariable Long id) {
		Decision decision = getService().getOne(id);
		Result.calculateOptionsTotalScores(decision);
		getService().saveAllDecisionOptions(decision.getOptions());
		return decision.getOptions();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions")
	public Long addDecision(@RequestBody Decision decision) {
		getService().save(decision);
		return decision.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/decisions/{id:\\d+}")
	public void updateDecision(@RequestBody Decision decision, Long id) {
		getService().save(decision);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/decisions/{decisionId}/scores")
	public void deleteDecisionsScores(@PathVariable Long decisionId) {
		getService().deleteAllScores(decisionId);
	}

}
