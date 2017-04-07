package ua.com.forkShop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.repository.FeatureStringRepository;
import ua.com.forkShop.service.FeatureStringService;
import ua.com.forkShop.service.specification.FeatureStringSpecification;

@Service
public class FeatureStringServiceImpl implements FeatureStringService {

	@Autowired
	private FeatureStringRepository featureStringRepository;

	@Override
	public List<FeatureString> findAll() {
		return featureStringRepository.findAll();
	}

	@Override
	public void delete(int id) {
		featureStringRepository.delete(id);
	}

	@Override
	public void save(FeatureString form) {
		featureStringRepository.save(form);
	}

	@Override
	public FeatureString findOne(int id) {
		return featureStringRepository.findOne(id);
	}

	@Override
	public FeatureString findOne(String name) {
		return featureStringRepository.findByName(name);
	}
	
	@Override
	public Page<FeatureString> findAll(BasicFilter filter, Pageable pageable) {
		return featureStringRepository.findAll(new FeatureStringSpecification(filter), pageable);
}
}
