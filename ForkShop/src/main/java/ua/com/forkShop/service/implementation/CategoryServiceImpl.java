package ua.com.forkShop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.NameOfFeatureDigital;
import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.repository.CategoryRepository;
import ua.com.forkShop.repository.NameOfFeatureDigitalRepository;
import ua.com.forkShop.repository.NameOfFeatureStringRepository;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.specification.CategorySpecification;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NameOfFeatureDigitalRepository nameOfFeatureDigitalRepository;
	
	@Autowired
	private NameOfFeatureStringRepository nameOfFeatureStringRepository;
	
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
	@Transactional
	public void addNofs(int id, int nofsId) {
		Category category = categoryRepository.loadedNofs(id);
		NameOfFeatureString nameOfFeatureString = nameOfFeatureStringRepository.findOne(nofsId);
		category.getNameOfFeatureStrings().add(nameOfFeatureString);
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void addNofd(int id, int nofdId) {
		Category category = categoryRepository.loadedNofd(id);
		NameOfFeatureDigital nameOfFeatureDigital = nameOfFeatureDigitalRepository.findOne(nofdId);
		category.getNameOfFeatureDigitals().add(nameOfFeatureDigital);
		categoryRepository.save(category);
	}

	@Override
	public Category loadedNofs(int id) {
		return categoryRepository.loadedNofs(id);
	}

	@Override
	public Category loadedNofd(int id) {
		return categoryRepository.loadedNofd(id);
	}

	@Override
	@Transactional
	public void deleteNofs(int id, int nofsId) {
		Category category = categoryRepository.loadedNofs(id);
		category.getNameOfFeatureStrings().removeIf(s->s.getId()==nofsId);
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void deleteNofd(int id, int nofdId) {
		Category category = categoryRepository.loadedNofd(id);
		category.getNameOfFeatureDigitals().removeIf(s->s.getId()==nofdId);
		categoryRepository.save(category);
	}

	@Override
	public Page<Category> findAll(BasicFilter filter, Pageable pageable) {
		return categoryRepository.findAll(new CategorySpecification(filter), pageable);
	}
}