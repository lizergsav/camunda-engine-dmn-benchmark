package org.camunda.bpm.dmn.engine.errors;

public class WrongDMNNameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dmnName;
	
	
	public WrongDMNNameException(String dmnName){
		this.setDmnName(dmnName);
		System.err.println("The "+this.getDmnName()+" DMN name is not existing");
	}


	public String getDmnName() {
		return dmnName;
	}


	public void setDmnName(String dmnName) {
		this.dmnName = dmnName;
	}
	
}
