package ua.com.forkShop.dto.rest;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

	private List<NameOfFeatureDiditalDto> nameOfFeatureDiditalDtos = new ArrayList<>();
	
	private List<NameOfFeatureStringDto> nameOfFeatureStringDtos = new ArrayList<>();

	public List<NameOfFeatureDiditalDto> getNameOfFeatureDiditalDtos() {
		return nameOfFeatureDiditalDtos;
	}

	public void setNameOfFeatureDiditalDtos(List<NameOfFeatureDiditalDto> nameOfFeatureDiditalDtos) {
		this.nameOfFeatureDiditalDtos = nameOfFeatureDiditalDtos;
	}

	public List<NameOfFeatureStringDto> getNameOfFeatureStringDtos() {
		return nameOfFeatureStringDtos;
	}

	public void setNameOfFeatureStringDtos(List<NameOfFeatureStringDto> nameOfFeatureStringDtos) {
		this.nameOfFeatureStringDtos = nameOfFeatureStringDtos;
	}
	
	
}
