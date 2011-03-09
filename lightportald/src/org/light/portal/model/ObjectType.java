package org.light.portal.model;

public class ObjectType extends Entity {
	private String name;
	
	public ObjectType(){
		super();
	}
	public ObjectType(long id, String name){
		this();
		this.setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
