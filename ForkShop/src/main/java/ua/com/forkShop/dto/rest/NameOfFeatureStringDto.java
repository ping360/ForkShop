package ua.com.forkShop.dto.rest;

import java.util.List;

public class NameOfFeatureStringDto {

	private String name;
	
	private List<FeatureStringDto> featureStringDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FeatureStringDto> getFeatureStringDto() {
		return featureStringDto;
	}

	public void setFeatureString(List<FeatureStringDto> featureStringDto) {
		this.featureStringDto = featureStringDto;
	}
	
	
}
