package kisch.binyamin.decidely.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FACTORS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Factor extends ModelEntity {
	
	@NotNull
	private Integer weight;
	@NotNull
	private Integer score;
	@ManyToOne
	@JsonBackReference
	@JoinTable(name = "OPTIONS_FACTORS",
			joinColumns = @JoinColumn(name = "FACTOR_ID"),
			inverseJoinColumns = @JoinColumn(name = "OPTION_ID")
	)
	private Option option;
	
	public Factor() {
		this(null, 0, 0);
	}
	
	public Factor(String text, Integer weight, Integer score) {
		this(text, weight, score, null);
	}
	
	public Factor(String text, Integer weight, Integer score, Option option) {
		super(text);
		this.weight = weight;
		this.score = score;
		this.option = option;
	}

	public Integer getWeight() {
		return weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
