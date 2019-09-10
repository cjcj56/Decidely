package kisch.binyamin.decidely.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static kisch.binyamin.decidely.DecidelyConsts.FACTOR_MAX_WEIGHT;

@Entity
@Table(name = "FACTORS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Factor extends TextEntity {
	
	@Min(value = -1)
	@Max(FACTOR_MAX_WEIGHT)
	private int weight;
	
	@ManyToOne
	@JsonBackReference
	private Decision decision;

	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!super.equals(other)) {
			return false;
		}
		return this.weight == Factor.class.cast(other).weight;
	}
}
