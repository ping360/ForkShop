package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Brand;

public interface BrandService {

	List<Brand> findAll();
	
	void delete(int id);
	
	void save(Brand form);
	
	Brand findOne(int id);

	Brand findOne(String name);
	
	Page<Brand> findAll(BasicFilter filter, Pageable pageable);
}
