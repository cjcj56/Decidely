package kisch.binyamin.decidely.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "OPTIONS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Option extends TextEntity implements Comparable<Option> {
	
	@Min(-1)
	private double totalScore;
	
	@ManyToOne
	@JsonBackReference
	private Decision decision;
	
	public double getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	
	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	@Override
	public int compareTo(Option other) {
		return Double.compare(this.totalScore, other.getTotalScore());
	}
}
