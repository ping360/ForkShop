package ua.com.forkShop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.repository.BrandRepository;
import ua.com.forkShop.service.BrandService;
import ua.com.forkShop.service.specification.BrandSpecification;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public void delete(int id) {
		brandRepository.delete(id);
	}

	@Override
	public void save(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public Brand findOne(int id) {
		return brandRepository.findOne(id);
	}

	@Override
	public Brand findOne(String name) {
		return brandRepository.findByName(name);
	}
	
	@Override
	public Page<Brand> findAll(BasicFilter filter, Pageable pageable) {
		return brandRepository.findAll(new BrandSpecification(filter), pageable);
}
}
