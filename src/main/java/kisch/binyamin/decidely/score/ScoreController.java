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
	
	@RequestMapping(method = RequestMethod.POST, value = "/decisions/{decisionId}/scores")
	public List<Long> addScoresToDecisionId(@RequestBody Scores scores, @PathVariable Long decisionId) {
		getService().saveAllToDecisionId(scores.getEntities(), decisionId);
		return scores.extractIds();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/scores")
	public void updateScores(@RequestBody Scores scores) {
		getService().saveAll(scores.getEntities());
	}
}
