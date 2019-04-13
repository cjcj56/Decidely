package kisch.binyamin.decidely.option;

import java.util.List;

import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.model.ModelEntityService;
import kisch.binyamin.decidely.model.Option;

@Service
public class OptionService extends ModelEntityService<Option, OptionRepository>{

	public List<Option> findByDecisionId(Long decisionId) {
		return getRepository().findByDecisionId(decisionId);
	}
	
}
