package kisch.binyamin.decidely.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SCORES")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Score extends ModelEntity {
	
	@NotNull
	@ManyToOne
	private Option option;
	
	@NotNull
	@ManyToOne
	private Factor factor;
	
	@Min(value = 0)
	@Max(value = 10)
	private int score;
	
	public Option getOption() {
		return option;
	}
	
	public void setOption(Option option) {
		this.option = option;
	}
	
	public Factor getFactor() {
		return factor;
	}
	
	public void setFactor(Factor factor) {
		this.factor = factor;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String generateMapKey() {
		return getOption().getId() + ":" + getFactor().getId();
	}

}
