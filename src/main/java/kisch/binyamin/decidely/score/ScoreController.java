package kisch.binyamin.decidely.score;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kisch.binyamin.decidely.model.ModelEntityController;
import kisch.binyamin.decidely.model.Score;

@RestController
public class ScoreController extends ModelEntityController<Score, ScoreService> {
	
	@RequestMapping("/scores")
	public List<Score> findAllFactors() {
		return getService().findAll();
	}
	
	@RequestMapping("/scores/{id}")
	public Score getFactor(@PathVariable Long id) {
		return getService().getOne(id);
	}
	
	@RequestMapping("/decisions/{decisionId}/scores")
	public Map<String, Score> findScoresByDecisionId(@PathVariable Long decisionId) {
		return getService().findByDecisionId(decisionId);
	}
	
	@RequestMapping("/decisions/{decisionId}/scores/{scoreId}")
	public Score getScore(@PathVariable Long decisionId, @PathVariable Long scoreId) {
		return getService().findByIdAndDecisionId(scoreId, decisionId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/scores")
	public void addFactor(@RequestBody Score score) {
		getService().save(score);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/options/{optionId}/factors/{factorId}/scores")
	public void addFactor(@RequestBody Score score, @PathVariable Long optionId, @PathVariable Long factorId) {
		getService().saveToOptionIdAndFactorId(score, optionId, factorId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/scores/{id}")
	public void updateScore(@RequestBody Score score, @PathVariable Long id) {
		if(getService().existsById(id)) {
			score.setId(id);
			getService().save(score);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/options/{optionId}/factors/{factorId}/scores/{scoreId}")
	public void updateFactor(@RequestBody Score score, @PathVariable Long optionId, Long factorId, Long scoreId) {
		if(getService().existsByIdAndOptionIdAndFactorId(scoreId, optionId, factorId)) {
			getService().saveToOptionIdAndFactorId(score, optionId, factorId);
		}
	}
}
