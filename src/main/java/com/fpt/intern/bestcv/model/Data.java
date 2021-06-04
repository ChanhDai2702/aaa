package com.fpt.intern.bestcv.model;

public class Data {
	public String label;
	private double value;
	

	public Data(String label, double d) {
		super();
		this.label = label;
		this.value = d;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Data [label=" + label + ", value=" + value + "]";
	}

	

}
