package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.FeatureString;

public interface FeatureStringService {

	List<FeatureString> findAll();
	
	void delete(int id);
	
	void save(FeatureString form);
	
	FeatureString findOne(int id);
	
	FeatureString findOne(String name);
	
	Page<FeatureString> findAll(BasicFilter filter, Pageable pageable);
}
