package kisch.binyamin.decidely.option;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityController;
import kisch.binyamin.decidely.model.Option;

@RestController
public class OptionController extends ModelEntityController<Option, OptionService>{
	
	@RequestMapping("/options")
	public List<Option> findAllOptions() {
		return getService().findAll();
	}
	
	@RequestMapping("/decisions/{decisionId}/options")
	public List<Option> findOptionsByDecision(@PathVariable Long decisionId) {
		return getService().findByDecisionId(decisionId);
	}
	
	@RequestMapping("/options/{id}")
	public Option getOption(@PathVariable Long id) {
		return getService().getOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/options")
	public void addOption(@RequestBody Option option) {
		getService().save(option);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions/{decisionId}/options")
	public void addOption(@RequestBody Option option, @PathVariable Long decisionId) {
		option.setDecision(new Decision(decisionId));
		getService().save(option);
	}
	
}
