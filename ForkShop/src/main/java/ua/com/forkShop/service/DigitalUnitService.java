package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.DigitalUnit;

public interface DigitalUnitService {

	List<DigitalUnit> findAll();
	
	void delete(int id);

	void save(DigitalUnit form);
	
	DigitalUnit findOne(int id);
	
	DigitalUnit findOne(String name);
	
	Page<DigitalUnit> findAll(BasicFilter filter, Pageable pageable);
	
}
