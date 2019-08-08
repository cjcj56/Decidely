package kisch.binyamin.decidely.result;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.model.Score;

public class Result {

	public static void calculateOptionsTotalScores(Decision decision) {
		Map<Long, Double> optionsScores =
				decision.getOptions().stream().map(opt -> opt.getId())
				.collect(Collectors.toMap(Function.identity(), x -> 0.0));
		for (Score score : decision.getScores().values()) {
			Long optId = score.getOption().getId();
			optionsScores.put(optId, optionsScores.get(optId) + score.getScore() * score.getFactor().getWeight());
		}
		for(Option option : decision.getOptions()) {
			option.setTotalScore(optionsScores.get(option.getId()));
		}
		decision.getOptions().sort(null);
		Collections.reverse(decision.getOptions());
	}

}
