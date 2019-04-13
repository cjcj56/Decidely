package kisch.binyamin.decidely.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ModelEntityController<E extends ModelEntity, S extends ModelEntityService<E, ? extends JpaRepository<E,Long>>> {

	@Autowired
	S service;

	public S getService() {
		return service;
	}

	public void setService(S service) {
		this.service = service;
	}
	
}
