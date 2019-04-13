package kisch.binyamin.decidely.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "DECISIONS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Decision extends ModelEntity {
	
	@OneToMany
	@JsonManagedReference
	@JoinTable(name = "DECISIONS_OPTIONS",
		joinColumns = @JoinColumn(name = "DECISION_ID"),
		inverseJoinColumns = @JoinColumn(name = "OPTION_ID")
	)
	private List<Option> options;
	
	public Decision() {
		this(null, new ArrayList<Option>());
	}
	
	public Decision(Long decisionId) {
		this();
		setId(decisionId);
	}

	public Decision(String text, List<Option> options) {
		super(text);
		this.options = options;
	}
	
	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
}
