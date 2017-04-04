package ua.com.forkShop.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.dto.rest.NameOfFeatureDiditalDto;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.entity.FeatureDigital;
import ua.com.forkShop.entity.NameOfFeatureDigital;
import ua.com.forkShop.repository.CategoryRepository;
import ua.com.forkShop.repository.NameOfFeatureDigitalRepository;
import ua.com.forkShop.service.NameOfFeatureDigitalService;
import ua.com.forkShop.service.specification.NameOfFeatureDigitalExcludeSpecification;
import ua.com.forkShop.service.specification.NameOfFeatureDigitalSpecification;

@Service
public class NameOfFeatureDigitalServiceImpl implements NameOfFeatureDigitalService {

	@Autowired
	private NameOfFeatureDigitalRepository nameOfFeatureDigitalRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NameOfFeatureDigital> findAll(){
		return nameOfFeatureDigitalRepository.findAll();
	}
	
	@Override
	public void delete(int id){
		nameOfFeatureDigitalRepository.delete(id);
	}

	@Override
	public void save(NameOfFeatureDigital form) {
		nameOfFeatureDigitalRepository.save(form);
	}

	@Override
	public List<NameOfFeatureDigital> findByCategoryId(int id) {
		return nameOfFeatureDigitalRepository.findByCategoryId(id);
	}
	
	@Override
	public NameOfFeatureDigital findOne(int id) {
		return nameOfFeatureDigitalRepository.findOne(id);
	}

	@Override
	public NameOfFeatureDigital findOne(String name) {
		return nameOfFeatureDigitalRepository.findByName(name);
	}

	@Override
	public Page<NameOfFeatureDigital> findAll(BasicFilter filter, Pageable pageable) {
		return nameOfFeatureDigitalRepository.findAll(new NameOfFeatureDigitalSpecification(filter), pageable);
	}

	@Override
	public Page<NameOfFeatureDigital> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category) {
		return nameOfFeatureDigitalRepository.findAll(new NameOfFeatureDigitalExcludeSpecification(filter, category), pageable);
	}

	@Override
	@Transactional
	public List<NameOfFeatureDigital> findAllLoadedFD() {
		List<NameOfFeatureDigital> list = nameOfFeatureDigitalRepository.findAllLoadedFD();
		for (NameOfFeatureDigital nameOfFeatureDigital : list) {
			for (FeatureDigital featureDigital : nameOfFeatureDigital.getFeatureDigitals()){
				Hibernate.initialize(featureDigital.getDigitalUnits());
				for(DigitalUnit du : featureDigital.getDigitalUnits()){
					if(!nameOfFeatureDigital.getDu().contains(du)) nameOfFeatureDigital.getDu().add(du);
				}
			}
		}
		return list;
	}

	@Override
	public List<NameOfFeatureDiditalDto> findByCategoryIdDto(int id) {
		return findByCategoryId(id).stream().map(this::mapper).collect(Collectors.toList());
	}
	
	private NameOfFeatureDiditalDto mapper(NameOfFeatureDigital digital){
		NameOfFeatureDiditalDto dto = new NameOfFeatureDiditalDto();
		dto.setId(digital.getId());
		dto.setName(digital.getName());
		return dto;
	}
}
