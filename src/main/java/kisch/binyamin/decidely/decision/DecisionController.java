package kisch.binyamin.decidely.decision;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityController;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions")
	public Long addDecision(@RequestBody Decision decision) {
		getService().save(decision);
		return decision.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/decisions/{id:\\d+}")
	public void updateDecision(@RequestBody Decision decision, Long id) {
		getService().save(decision);
	}

}
