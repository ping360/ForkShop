package ua.com.forkShop.dto.form;

import java.util.ArrayList;
import java.util.List;

import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.entity.NameOfFeatureDigital;

public class FeatureDigitalForm {

	private int id;

	private String value;

	private NameOfFeatureDigital nameOfFeatureDigital;

	private List<DigitalUnit> digitalUnits = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public NameOfFeatureDigital getNameOfFeatureDigital() {
		return nameOfFeatureDigital;
	}

	public void setNameOfFeatureDigital(NameOfFeatureDigital nameOfFeatureDigital) {
		this.nameOfFeatureDigital = nameOfFeatureDigital;
	}

	public List<DigitalUnit> getDigitalUnits() {
		return digitalUnits;
	}

	public void setDigitalUnits(List<DigitalUnit> digitalUnits) {
		this.digitalUnits = digitalUnits;
	}
	
	
}
