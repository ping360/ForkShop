package ua.com.forkShop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.repository.DigitalUnitRepository;
import ua.com.forkShop.service.DigitalUnitService;
import ua.com.forkShop.service.specification.DigitalUnitSpecification;


@Service
public class DigitalUnitServiceImpl implements DigitalUnitService{

	@Autowired
	private DigitalUnitRepository digitalUnitRepository;
	
	@Override
	public List<DigitalUnit> findAll(){
		return digitalUnitRepository.findAll();
	}
	
	@Override
	public void delete(int id){
		digitalUnitRepository.delete(id);
	}
	
	@Override
	public void save(DigitalUnit form) {
		digitalUnitRepository.save(form);
	}

	@Override
	public DigitalUnit findOne(int id) {
		return digitalUnitRepository.findOne(id);
	}

	@Override
	public DigitalUnit findOne(String name) {
		return digitalUnitRepository.findByName(name);
	}

	@Override
	public Page<DigitalUnit> findAll(BasicFilter filter, Pageable pageable) {
		return digitalUnitRepository.findAll(new DigitalUnitSpecification(filter), pageable);
}
}
