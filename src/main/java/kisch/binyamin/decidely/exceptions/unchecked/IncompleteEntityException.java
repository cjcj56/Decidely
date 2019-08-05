package kisch.binyamin.decidely.exceptions.unchecked;

import javax.persistence.PersistenceException;

public class IncompleteEntityException extends PersistenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5108180111463417978L;
	
	public IncompleteEntityException() {
		super();
	}
	
	public IncompleteEntityException(String message) {
		super(message);
	}
	
	public IncompleteEntityException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IncompleteEntityException(Throwable cause) {
		super(cause);
	}

}
