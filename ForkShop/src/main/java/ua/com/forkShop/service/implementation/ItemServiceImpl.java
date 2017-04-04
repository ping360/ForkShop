package ua.com.forkShop.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.forkShop.dto.filter.ItemFilter;
import ua.com.forkShop.dto.form.FeatureDigitalForm;
import ua.com.forkShop.dto.form.ItemForm;
import ua.com.forkShop.entity.FeatureDigital;
import ua.com.forkShop.entity.Item;
import ua.com.forkShop.repository.BrandRepository;
import ua.com.forkShop.repository.CategoryRepository;
import ua.com.forkShop.repository.DigitalUnitRepository;
import ua.com.forkShop.repository.FeatureDigitalRepository;
import ua.com.forkShop.repository.FeatureStringRepository;
import ua.com.forkShop.repository.ItemRepository;
import ua.com.forkShop.repository.NameOfFeatureDigitalRepository;
import ua.com.forkShop.service.FileWriter;
import ua.com.forkShop.service.FileWriter.Folder;
import ua.com.forkShop.service.ItemService;
import ua.com.forkShop.service.specification.ItemSpecification;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FeatureStringRepository featureStringRepository;

	@Autowired
	private FeatureDigitalRepository featureDigitalRepository;

	@Autowired
	private NameOfFeatureDigitalRepository nameOfFeatureDigitalRepository;

	@Autowired
	private DigitalUnitRepository digitalUnitRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private FileWriter fileWriter;

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@Override
	public List<Item> findByCategoryId(int id) {
		return itemRepository.findByCategoryId(id);
	}

	@Override
	@Transactional
	public void save(ItemForm itemForm) {
		List<FeatureDigital> fds = new ArrayList<FeatureDigital>();
		for (FeatureDigitalForm featureDigital : itemForm.getFeatureDigitals()) {
			FeatureDigital fd = featureDigitalRepository.findByNofdValue(
					featureDigital.getNameOfFeatureDigital().getId(),
					new BigDecimal(featureDigital.getValue().replace(',', '.')));
			if (fd == null) {
				fd = new FeatureDigital();
				fd.setDigitalUnits(featureDigital.getDigitalUnits());
				fd.setNameOfFeatureDigital(featureDigital.getNameOfFeatureDigital());
				fd.setValue(new BigDecimal(featureDigital.getValue()));
			}
			featureDigitalRepository.save(fd);
			fds.add(fd);
		}
		Item item = new Item();
		item.setId(itemForm.getId());
		item.setName(itemForm.getName());
		item.setCategory(itemForm.getCategory());
		item.setBrand(itemForm.getBrand());
		item.getDigitalUnits().addAll(itemForm.getDigitalUnits());
		item.setFeatureStrings(itemForm.getFeatureStrings());
		item.setPrice(new BigDecimal(itemForm.getPrice().replace(',', '.')));
		item = itemRepository.saveAndFlush(item);
		item.setFeatureDigitals(fds);
		if (fileWriter.write(Folder.ITEM, itemForm.getFile(), item.getId())) {
			if (item.getVersion() == null)
				item.setVersion(0);
			else
				item.setVersion(item.getVersion() + 1);
		}
		itemRepository.save(item);
	}

	@Override
	public Page<Item> findAll(ItemFilter filter, Pageable pageable) {
		System.out.println("-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-");
		Page<Item> items = itemRepository.findAll(new ItemSpecification(filter), pageable);
		System.out.println("-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-*-v-");
		return items;
	}

	@Override
	public int findCount(int id) {
		Integer count = itemRepository.findCount(id);
		if (count == null)
			return 0;
		return count;
	}

	@Override
	public List<Item> findByUserId(int userId) {
		return itemRepository.findByUserId(userId);
	}
}