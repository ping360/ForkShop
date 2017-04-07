package ua.com.forkShop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.repository.CategoryRepository;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.specification.CategorySpecification;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category findOne(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Page<Category> findAll(BasicFilter filter, Pageable pageable) {
		return categoryRepository.findAll(new CategorySpecification(filter), pageable);
	}
}