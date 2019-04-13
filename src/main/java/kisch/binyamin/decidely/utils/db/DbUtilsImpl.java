package kisch.binyamin.decidely.utils.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
import kisch.binyamin.decidely.option.OptionRepository;

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
	
	public DbUtilsImpl() {
	}
	
	@PostConstruct
	public void initTestData() {
		
		List<Decision> decisions = new ArrayList<Decision>(N_DECISIONS);
		List<Option> options = new ArrayList<Option>(N_OPTIONS);
		List<Factor> factors = new ArrayList<Factor>(N_FACTORS);
		
		for(int i = 0; i < N_DECISIONS; ++i) {
			decisions.add(new Decision());
			decisions.get(i).setText(Decision.class.getSimpleName() + " " + i);
		}
		for(int i = 0; i < N_OPTIONS; ++i) {
			Option option = new Option();
			int dec_i = i % N_DECISIONS;
			option.setText(Option.class.getSimpleName() + " " + dec_i + "_" + i);
			options.add(option);
		}
		for(int i = 0; i < N_FACTORS; ++i) {
			Factor factor = new Factor();
			int opt_i = i % N_OPTIONS;
			int dec_i = i % N_DECISIONS;
			factor.setScore(rand.nextInt(N) + 1);
			factor.setWeight(rand.nextInt(N) + 1);
			factor.setText("Factor " + dec_i + "_" + opt_i + "_" + i);
			factors.add(factor);
		}
		
		// persist unrelated entities
		for (Decision decision : decisions) decisionRepository.save(decision);
		for (Option option : options) optionRepository.save(option);
		for (Factor factor : factors) factorRepository.save(factor);
		
		// add relationships between entities
		for(int i = 0; i < N_OPTIONS; ++i) {
			int dec_i = i % N_DECISIONS;
			options.get(i).setDecision(decisions.get(dec_i));
			decisions.get(dec_i).getOptions().add(options.get(i));
		}
		for(int i = 0; i < N_FACTORS; ++i) {
			int opt_i = i % N_OPTIONS;
			factors.get(i).setOption(options.get(opt_i));
			options.get(opt_i).getFactors().add(factors.get(i));
		}
		
		// persist relations
		for (Decision decision : decisions) decisionRepository.save(decision);
		for (Option option : options) optionRepository.save(option);
		for (Factor factor : factors) factorRepository.save(factor);
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
