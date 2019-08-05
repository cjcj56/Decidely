package kisch.binyamin.decidely.factor;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import kisch.binyamin.decidely.exceptions.unchecked.IncompleteEntityException;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.ModelEntityService;

import static kisch.binyamin.decidely.utils.db.DbMessagesConsts.*;

@Service
public class FactorService extends ModelEntityService<Factor, FactorRepository>{

	public List<Factor> findByDecisionId(Long decisionId) {
		return getRepository().findByDecisionId(decisionId);
	}
	
	public Factor findByIdAndDecisionId(Long factorId, Long decisionId) {
		List<Factor> factors = getRepository().findByDecisionId(decisionId)
				.stream().filter(f -> f.getId().equals(factorId))
				.collect(Collectors.toList());
		return factors.size() > 0 ? factors.get(0) : null;
	}

	public void saveToDecisionId(Factor factor, Long decisionId) {
		Decision newDecision = getServicingService().getDecisionService().getOne(decisionId);
		if(newDecision == null) {
			throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, "Decision", decisionId));
		}
		Decision decision = factor.getDecision();
		if((decision != null) && (!decision.getId().equals(decisionId))) {
			decision.getFactors().remove(factor);
			getServicingService().getDecisionService().save(decision);
		}
		factor.setDecision(newDecision);
		save(factor);
		if(!newDecision.getFactors().contains(factor)) {
			newDecision.getFactors().add(factor);
			getServicingService().getDecisionService().save(newDecision);
		}
	}

	public boolean existsByIdAndDecisionId(Long factorId, Long decisionId) {
		Factor factor = getOne(factorId);
		if(factor == null) {
			return false;
		}
		Decision decision = factor.getDecision();
		if(decision == null) {
			throw new IncompleteEntityException(String.format(ENTITY_IS_INCOMPLETE, "Factor", factorId)); 
		}
		return decisionId.equals(decision.getId());
	}

}
