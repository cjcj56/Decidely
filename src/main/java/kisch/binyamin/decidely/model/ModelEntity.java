package kisch.binyamin.decidely.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class ModelEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	private boolean deleted = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		if(!(this.getClass().isInstance(other) && other.getClass().isInstance(this))) {
			return false;
		}
		return this.id.equals(this.getClass().cast(other).getId());
	}
	
}
