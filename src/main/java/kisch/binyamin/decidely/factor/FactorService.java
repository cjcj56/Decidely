package kisch.binyamin.decidely.factor;

import java.util.List;

import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.ModelEntityService;

@Service
public class FactorService extends ModelEntityService<Factor, FactorRepository>{

	public List<Factor> findByOptionId(Long optionId) {
		return getRepository().findByOptionId(optionId);
	}

	public List<Factor> findByDecisionIdAndOptionId(Long decisionId, Long optionId) {
		return getRepository().findByDecisionIdAndOptionId(decisionId, optionId);
	}

	public boolean existsByIdAndOptionId(Long factorId, Long optionId) {
		return getRepository().existsByIdAndOptionId(factorId, optionId);
	}

	public boolean existsByIdAndOptionIdAndDecisionId(Long factorId, Long optionId, Long decisionId) {
		return getRepository().existsByIdAndOptionIdAndDecisionId(factorId, optionId, decisionId);
	}

}
