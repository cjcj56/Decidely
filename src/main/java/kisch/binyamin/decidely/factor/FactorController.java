package kisch.binyamin.decidely.factor;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.ModelEntityController;
import kisch.binyamin.decidely.model.Option;

@RestController
public class FactorController extends ModelEntityController<Factor, FactorService> {
	
	@RequestMapping("/factors")
	public List<Factor> findAllFactors() {
		return getService().findAll();
	}
	
	@RequestMapping("/options/{optionId}/factors")
	public List<Factor> findFactorsByOptionId(@PathVariable Long optionId) {
		return getService().findByOptionId(optionId);
	}
	
	@RequestMapping("/decisions/{decisionId}/options/{optionId}/factors")
	public List<Factor> findFactorsByOptionIdAndDecisionId(@PathVariable Long decisionId, @PathVariable Long optionId) {
		return getService().findByDecisionIdAndOptionId(decisionId, optionId);
	}
	
	@RequestMapping("/factors/{id}")
	public Factor getFactor(@PathVariable Long id) {
		return getService().getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/factors")
	public void addFactor(@RequestBody Factor factor) {
		getService().save(factor);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/options/{optionId}/factors")
	public void addFactor(@RequestBody Factor factor, @PathVariable Long optionId) {
		factor.setOption(new Option(optionId));
		getService().save(factor);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions/{decisionId}/options/{optionId}/factors")
	public void addFactor(@RequestBody Factor factor, @PathVariable Long decisionId, @PathVariable Long optionId) {
		factor.setOption(new Option(optionId));
		factor.getOption().setDecision(new Decision(decisionId));
		getService().save(factor);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/factors/{id}")
	public void updateFactor(@RequestBody Factor factor, @PathVariable Long id) {
		if(getService().existsById(id)) {
			factor.setId(id);
			getService().save(factor);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/options/{optionId}/factors/{factorId}")
	public void updateFactor(@RequestBody Factor factor, @PathVariable Long optionId, Long factorId) {
		if(getService().existsByIdAndOptionId(factorId, optionId)) {
			factor.setOption(new Option(optionId));
			factor.setId(factorId);
		getService().save(factor);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/decisions/{decisionId}/options/{optionId}/factors/{factorId}")
	public void updateFactor(@RequestBody Factor factor, @PathVariable Long decisionId, @PathVariable Long optionId, @PathVariable Long factorId) {
		/* TODO : what is better performance-wise - querying all at once? or first by id then by optionId (only one join table)?
		          consider also that most api calls by id - there is no real need to check id  */
		if(getService().existsByIdAndOptionIdAndDecisionId(factorId, optionId, decisionId)) {
			factor.setOption(new Option(optionId));
			factor.getOption().setDecision(new Decision(decisionId));
			factor.setId(factorId);
			getService().save(factor);
		}
	}
	
}
