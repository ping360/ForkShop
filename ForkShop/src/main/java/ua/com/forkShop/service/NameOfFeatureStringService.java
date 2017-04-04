package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.dto.rest.NameOfFeatureStringDto;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.NameOfFeatureString;

public interface NameOfFeatureStringService {

	List<NameOfFeatureString> findAll();
	
	List<NameOfFeatureString> findAllLoadedFS();
	
	void delete(int id);
	
	void save(NameOfFeatureString form);
	
	List<NameOfFeatureString> findByCategoryId(int id);
	
	List<NameOfFeatureStringDto> findByCategoryIdDto(int id);
	
	NameOfFeatureString findOne(int id);
	
	NameOfFeatureString findOne(String name);
	
	Page<NameOfFeatureString> findAll(BasicFilter filter, Pageable pageable);

	Page<NameOfFeatureString> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category);
}
