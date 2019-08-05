package kisch.binyamin.decidely.factor;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.ModelEntityController;

@RestController
public class FactorController extends ModelEntityController<Factor, FactorService> {
	
	@RequestMapping("/factors")
	public List<Factor> findAllFactors() {
		return getService().findAll();
	}
	
	@RequestMapping("/factors/{id}")
	public Factor getFactor(@PathVariable Long id) {
		return getService().getOne(id);
	}
	
	@RequestMapping("/decisions/{decisionId}/factors")
	public List<Factor> findFactorsByDecisionId(@PathVariable Long decisionId) {
		return getService().findByDecisionId(decisionId);
	}
	
	@RequestMapping("/decisions/{decisionId}/factors/{factorId}")
	public Factor getFactor(@PathVariable Long decisionId, @PathVariable Long factorId) {
		return getService().findByIdAndDecisionId(factorId, decisionId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/factors")
	public Long addFactor(@RequestBody Factor factor) {
		getService().save(factor);
		return factor.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/factors/{id}")
	public void updateFactor(@RequestBody Factor factor, @PathVariable Long id) {
		if(getService().existsById(id)) {
			factor.setId(id);
			getService().save(factor);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/decision/{decisionId}/factors/{factorId}")
	public void updateFactor(@RequestBody Factor factor, @PathVariable Long decisionId, @PathVariable Long factorId) {
		if(getService().existsByIdAndDecisionId(factorId, decisionId)) {
			getService().saveToDecisionId(factor, decisionId);
		}
	}
		
}
