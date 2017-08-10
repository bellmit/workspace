package org.jumao.bi.entites.baidu;

import java.io.Serializable;

public class VistorBean implements Serializable {

	private static final long serialVersionUID = 878983885935111016L;
	String name;
	String newNumber;
	String oldNumber;

	/**
	 * Creates a new instance of VistorBean
	 * 
	 * @param name
	 * @param newNumber
	 * @param oldNumber
	 */
	public VistorBean(String name, String newNumber, String oldNumber) {
		this.name = name;
		this.newNumber = newNumber;
		this.oldNumber = oldNumber;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewNumber() {
		return newNumber;
	}

	public void setNewNumber(String newNumber) {
		this.newNumber = newNumber;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}

}
