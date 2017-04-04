package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.dto.rest.NameOfFeatureDiditalDto;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.NameOfFeatureDigital;

public interface NameOfFeatureDigitalService {

	List<NameOfFeatureDigital> findAll();

	void delete(int id);

	void save(NameOfFeatureDigital nameOfFeatureDigital);

	List<NameOfFeatureDigital> findByCategoryId(int id);

	NameOfFeatureDigital findOne(int id);

	NameOfFeatureDigital findOne(String name);

	Page<NameOfFeatureDigital> findAll(BasicFilter filter, Pageable pageable);

	Page<NameOfFeatureDigital> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category);

	List<NameOfFeatureDigital> findAllLoadedFD();

	List<NameOfFeatureDiditalDto> findByCategoryIdDto(int i);
}
