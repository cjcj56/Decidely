package kisch.binyamin.decidely.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "OPTIONS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Option extends ModelEntity {
	
	@OneToMany
	@JsonManagedReference
	@JoinTable(name = "OPTION_FACTORS",
			joinColumns = @JoinColumn(name = "OPTION_ID"),
			inverseJoinColumns = @JoinColumn(name = "FACTOR_ID")
	)
	private List<Factor> factors;
	@ManyToOne
	@JsonBackReference
	@JoinTable(name = "DECISIONS_OPTIONS",
			joinColumns = @JoinColumn(name = "OPTION_ID"),
			inverseJoinColumns = @JoinColumn(name = "DECISION_ID")
	)
	private Decision decision;
	
	public Option() {
		this(null, new ArrayList<Factor>());
	}
	
	public Option(Long optionId) {
		this();
		setId(optionId);
	}

	public Option(String text, List<Factor> factors) {
		this(text, factors, null);
	}

	public Option(String text, List<Factor> factors, Decision decision) {
		super(text);
		this.factors = factors;
		this.decision = decision;
	}

	public List<Factor> getFactors() {
		return factors;
	}

	public void setFactors(List<Factor> factors) {
		this.factors = factors;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	
}
