package kisch.binyamin.decidely.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "DECISIONS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Decision extends TextEntity {
	
	@OneToMany
	@JsonManagedReference
	@JoinTable(name = "DECISIONS_OPTIONS", joinColumns = @JoinColumn(name = "DECISION_ID"),
		inverseJoinColumns = @JoinColumn(name = "OPTION_ID")
	)
	private List<Option> options = new ArrayList<Option>();
	
	@OneToMany
	@JsonManagedReference
	@JoinTable(name = "DECISIONS_FACTORS", joinColumns = @JoinColumn(name = "DECISION_ID"),
		inverseJoinColumns = @JoinColumn(name = "FACTOR_ID")
	)
	private List<Factor> factors = new ArrayList<Factor>();
	
	@OneToMany
	@MapKeyColumn(name = "SCORE_KEY")
	@JoinTable(joinColumns = {@JoinColumn(name = "DECISION_ID")},
		inverseJoinColumns = {@JoinColumn(name = "SCORE_ID")}
	)
	private Map<String, Score> scores = new HashMap<String, Score>(); 
	
	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<Factor> getFactors() {
		return factors;
	}
	
	public void setFactors(List<Factor> factors) {
		this.factors = factors;
	}

	public Map<String, Score> getScores() {
		return scores;
	}

	public void setScores(Map<String, Score> scores) {
		this.scores = scores;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!super.equals(other)) {
			return false;
		}
		Decision otherDecision = (Decision)other;
		if(!(new HashSet<Option>(this.getOptions())).equals(new HashSet<Option>(otherDecision.getOptions()))) {
			return false;
		}
		if(!(new HashSet<Factor>(this.getFactors())).equals(new HashSet<Factor>(otherDecision.getFactors()))) {
			return false;
		}
		return this.getScores().equals(otherDecision.getScores());
	}
	
}
