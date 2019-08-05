package kisch.binyamin.decidely.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TextEntity extends ModelEntity {
	
	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other) && this.text.contentEquals(this.getClass().cast(other).getText());
	}
	
}
