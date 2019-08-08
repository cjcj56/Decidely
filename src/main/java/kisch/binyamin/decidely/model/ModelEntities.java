package kisch.binyamin.decidely.model;

import java.util.List;
import java.util.stream.Collectors;

public class ModelEntities<E extends ModelEntity> {
	
	private List<E> entities;

	public List<E> getEntities() {
		return entities;
	}

	public void setEntities(List<E> entities) {
		this.entities = entities;
	}
	
	public List<Long> extractIds() {
		return entities.stream().map(e -> e.getId()).collect(Collectors.toList());
	}

}
