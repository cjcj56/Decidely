package kisch.binyamin.decidely.option;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.ModelEntityService;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.utils.db.DbMessagesConsts;

@Service
public class OptionService extends ModelEntityService<Option, OptionRepository> {

	public List<Option> findByDecisionId(Long decisionId) {
		return getRepository().findByDecisionId(decisionId);
	}

	public void saveToDecisionId(Option option, Long decisionId) {
		Decision decision = getServicingService().getDecisionService().getOne(decisionId);
		if(decision == null) {
			throw new EntityNotFoundException(String.format(DbMessagesConsts.ENTITY_NOT_FOUND_BY_ID, "Decision", decisionId));
		}
		option.setDecision(decision);
		decision.getOptions().add(option);
		save(option);
		getServicingService().getDecisionService().save(decision);
	}
	
}
