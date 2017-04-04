package ua.com.forkShop.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import ua.com.forkShop.dto.rest.CategoryDto;
import ua.com.forkShop.service.NameOfFeatureDigitalService;
import ua.com.forkShop.service.NameOfFeatureStringService;

public class CategoryControllerRest {

	@Autowired
	private NameOfFeatureDigitalService digitalService;
	
	@Autowired
	private NameOfFeatureStringService stringService;
	
	public CategoryDto getCategory(@PathVariable int id){
		CategoryDto dto = new CategoryDto();
		dto.setNameOfFeatureDiditalDtos(digitalService.findByCategoryIdDto(id));
		dto.setNameOfFeatureStringDtos(stringService.findByCategoryIdDto(id));
		return dto;
	}
}
