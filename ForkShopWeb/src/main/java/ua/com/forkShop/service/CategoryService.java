package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	void delete(int id);

	void save(Category category);

	Category findOne(int id);

	Category findOne(String name);

	Page<Category> findAll(BasicFilter filter, Pageable pageable);

	Category findOneByItem(int id);
}
