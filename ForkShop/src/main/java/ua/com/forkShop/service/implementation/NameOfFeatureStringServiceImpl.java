package ua.com.forkShop.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.dto.rest.FeatureStringDto;
import ua.com.forkShop.dto.rest.NameOfFeatureStringDto;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.entity.NameOfFeatureString;
import ua.com.forkShop.repository.CategoryRepository;
import ua.com.forkShop.repository.NameOfFeatureStringRepository;
import ua.com.forkShop.service.NameOfFeatureStringService;
import ua.com.forkShop.service.specification.NameOfFeatureStringExcludeSpecification;
import ua.com.forkShop.service.specification.NameOfFeatureStringSpecification;

@Service
public class NameOfFeatureStringServiceImpl implements NameOfFeatureStringService {

	@Autowired
	private NameOfFeatureStringRepository nameOfFeatureStringRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NameOfFeatureString> findAll(){
		return nameOfFeatureStringRepository.findAll();
	}
	
	public void delete(int id){
		nameOfFeatureStringRepository.delete(id);
	}
	
	@Override
	public void save(NameOfFeatureString form) {
		nameOfFeatureStringRepository.save(form);
	}

	@Override
	public List<NameOfFeatureString> findByCategoryId(int id) {
		return nameOfFeatureStringRepository.findByCategoryId(id);
	}
	
	@Override
	public NameOfFeatureString findOne(int id) {
		return nameOfFeatureStringRepository.findOne(id);
	}

	@Override
	public NameOfFeatureString findOne(String name) {
		return nameOfFeatureStringRepository.findByName(name);
	}

	@Override
	public Page<NameOfFeatureString> findAll(BasicFilter filter, Pageable pageable) {
		return nameOfFeatureStringRepository.findAll(new NameOfFeatureStringSpecification(filter), pageable);
	}

	@Override
	public Page<NameOfFeatureString> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category) {
		return nameOfFeatureStringRepository.findAll(new NameOfFeatureStringExcludeSpecification(filter, category), pageable);
	}

	@Override
	public List<NameOfFeatureString> findAllLoadedFS() {
		return nameOfFeatureStringRepository.findAllLoadedFS();
	}

	@Override
	public List<NameOfFeatureStringDto> findByCategoryIdDto(int id) {
		return findByCategoryId(id).stream().map(this::mapper).collect(Collectors.toList());
	}
	
	private FeatureStringDto mapper(FeatureString featureString){
		FeatureStringDto dto = new FeatureStringDto();
		dto.setName(featureString.getName());
		dto.setId(featureString.getId());
		return dto;
	}
	
	private NameOfFeatureStringDto mapper(NameOfFeatureString featureString){
		NameOfFeatureStringDto dto = new NameOfFeatureStringDto();
		dto.setName(featureString.getName());
		dto.setFeatureString(featureString.getFeatureStrings().stream().map(this::mapper).collect(Collectors.toList()));
		return dto;
	}
	
}
