package kisch.binyamin.decidely.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import kisch.binyamin.decidely.utils.db.ServicingService;

public abstract class ModelEntityService<E extends ModelEntity, R extends JpaRepository<E, Long>> {
	
	@Autowired
	private R repository;
	
	@Autowired
	private ServicingService servicingService;
	
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
	
	public void saveAll(Iterable<E> entities) {
		repository.saveAll(entities);
	}
	
	public void saveAndFlush(E entity) {
		repository.saveAndFlush(entity);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	public void flush() {
		getRepository().flush();
	}

	public R getRepository() {
		return repository;
	}

	public void setRepository(R repository) {
		this.repository = repository;
	}

	public ServicingService getServicingService() {
		return servicingService;
	}

	public void setServicingService(ServicingService servicingService) {
		this.servicingService = servicingService;
	}
	
	public void deleteAll(Iterable<? extends E> entities) {
		repository.deleteAll(entities);
	}
	
}
