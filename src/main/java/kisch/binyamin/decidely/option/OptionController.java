package kisch.binyamin.decidely.option;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kisch.binyamin.decidely.exceptions.unchecked.IncompleteEntityException;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityController;
import kisch.binyamin.decidely.model.Option;

import static kisch.binyamin.decidely.utils.db.DbMessagesConsts.ENTITY_IS_INCOMPLETE;

@RestController
public class OptionController extends ModelEntityController<Option, OptionService>{
	
	@RequestMapping("/options")
	public List<Option> findAllOptions() {
		return getService().findAll();
	}
	
	@RequestMapping("/options/{id}")
	public Option getOption(@PathVariable Long id) {
		return getService().getOne(id);
	}
	
	@RequestMapping("/decisions/{decisionId}/options")
	public List<Option> findOptionsByDecision(@PathVariable Long decisionId) {
		return getService().findByDecisionId(decisionId);
	}
	
	@RequestMapping("/decisions/{decisionId}/options/{optionId}")
	public Option getOption(@PathVariable Long decisionId, @PathVariable Long optionId) {
		Option option = getService().getOne(optionId);
		Decision decision = option.getDecision();
		if(decision == null) {
			throw new IncompleteEntityException(String.format(ENTITY_IS_INCOMPLETE, "Option", optionId));
		}
		return decisionId.equals(decision.getId()) ? option : null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions/{decisionId}/options")
	public List<Long> addOptions(@RequestBody Options options, @PathVariable Long decisionId) {
		getService().saveAllToDecisionId(options.getEntities(), decisionId);
		return options.extractIds();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/decisions/{decisionId}/options")
	public void updateOptions(@RequestBody Options options, @PathVariable Long decisionId) {
		getService().saveAllToDecisionId(options.getEntities(), decisionId);
	}
}
