package kisch.binyamin.decidely.utils.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kisch.binyamin.decidely.decision.DecisionRepository;
import kisch.binyamin.decidely.factor.FactorRepository;
import kisch.binyamin.decidely.model.Decision;
import kisch.binyamin.decidely.model.Factor;
import kisch.binyamin.decidely.model.Option;
import kisch.binyamin.decidely.model.Score;
import kisch.binyamin.decidely.option.OptionRepository;
import kisch.binyamin.decidely.result.Result;
import kisch.binyamin.decidely.score.ScoreRepository;

@Service
public class DbUtilsImpl implements DbUtils {

	public static final int N = 9;
	public static final int N_DECISIONS = 2;
	public static final int N_OPTIONS = 5;
	public static final int N_FACTORS = 20;
	public static final Random rand = new Random();
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DecisionRepository decisionRepository;
	@Autowired
	private OptionRepository optionRepository;
	@Autowired
	private FactorRepository factorRepository;
	@Autowired
	private ScoreRepository scoreRepository;
	
	public DbUtilsImpl() {
	}
	
	@PostConstruct
	public void initTestData() {
		
		List<Decision> decisions = new ArrayList<Decision>(N_DECISIONS);
		List<Option> options = new ArrayList<Option>(N_OPTIONS);
		List<Factor> factors = new ArrayList<Factor>(N_FACTORS);
		List<Score> scores = new ArrayList<Score>(N_FACTORS*N_OPTIONS);
		
		for(int i = 0; i < N_DECISIONS; ++i) {
			decisions.add(new Decision());
			decisions.get(i).setText(Decision.class.getSimpleName() + " #" + i);
		}
		for(int i = 0; i < N_OPTIONS; ++i) {
			Option option = new Option();
			int dec_i = i % N_DECISIONS;
			option.setText(Option.class.getSimpleName() + " #" + i + " for decision #" + dec_i);
			options.add(option);
			Decision d = decisions.get(dec_i); 
			d.getOptions().add(option);
			option.setDecision(d);
			
		}
		for(int i = 0; i < N_FACTORS; ++i) {
			Factor factor = new Factor();
			int dec_i = i % N_DECISIONS;
			factor.setWeight(rand.nextInt(N) + 1);
			factor.setText("Factor #" + i + " for decision #" + dec_i);
			factors.add(factor);
			Decision d = decisions.get(dec_i); 
			d.getFactors().add(factor);
			factor.setDecision(d);
		}
		
		for (Decision decision : decisions) {
			List<Option> dOptions = decision.getOptions();
			List<Factor> dFactors = decision.getFactors();
			decision.setOptions(new ArrayList<Option>());
			decision.setFactors(new ArrayList<Factor>());
			decisionRepository.saveAndFlush(decision);
			decision.setOptions(dOptions);
			decision.setFactors(dFactors);
		}
		for (Option option : options) optionRepository.saveAndFlush(option);
		for (Factor factor : factors) factorRepository.saveAndFlush(factor);
		
		for(int i = 0; i < N_DECISIONS; ++i) {
			Decision d = decisions.get(i);
			Map<String, Score> scoresMap = new HashMap<String, Score>(d.getOptions().size() * d.getFactors().size());
			for(Option o : d.getOptions()) {
				for(Factor f : d.getFactors()) {
					Score score = new Score();
					score.setOption(o);
					score.setFactor(f);
					score.setScore(rand.nextInt(N) + 1);
					scores.add(score);
					scoresMap.put(o.getId() + ":" + f.getId(), score);
				}
			}
			d.setScores(scoresMap);
		}
		
		// persist entities
		for (Score score : scores) scoreRepository.saveAndFlush(score);
		for (Decision decision : decisions) decisionRepository.saveAndFlush(decision); // Saving relationships from decisions' side
		
		// Calculate and persist results
		Result result = new Result();
		for(Decision decision : decisions) {
			result.calculateOptionsTotalScores(decision);
		}
		for (Option option : options) optionRepository.saveAndFlush(option);
	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDriverName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConnectionString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public DecisionRepository getDecisionRepository() {
		return decisionRepository;
	}

	public void setDecisionRepository(DecisionRepository decisionRepository) {
		this.decisionRepository = decisionRepository;
	}

	public OptionRepository getOptionRepository() {
		return optionRepository;
	}

	public void setOptionRepository(OptionRepository optionRepository) {
		this.optionRepository = optionRepository;
	}

	public FactorRepository getFactorRepository() {
		return factorRepository;
	}

	public void setFactorRepository(FactorRepository factorRepository) {
		this.factorRepository = factorRepository;
	}

}
