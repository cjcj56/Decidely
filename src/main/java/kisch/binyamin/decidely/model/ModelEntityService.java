package kisch.binyamin.decidely.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ModelEntityService<E extends ModelEntity, R extends JpaRepository<E, Long>> {
	
	@Autowired
	private R repository;
	
	public ModelEntityService() {
		super();
	}
	
	public List<E> findAll() {
		return repository.findAll();
	}
	
	public E getOne(Long id) {
		return repository.getOne(id);
	}
	
	public void save(E entity) {
		repository.save(entity);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public R getRepository() {
		return repository;
	}

	public void setRepository(R repository) {
		this.repository = repository;
	}

}
