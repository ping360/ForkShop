package ua.com.forkShop.service.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.NameOfFeatureDigital;

public class NameOfFeatureDigitalExcludeSpecification implements Specification<NameOfFeatureDigital> {

	private final BasicFilter filter;

	private final Category category;

	private final List<Predicate> predicates = new ArrayList<>();

	public NameOfFeatureDigitalExcludeSpecification(BasicFilter filter, Category category) {
		this.filter = filter;
		this.category = category;
	}

	@Override
	public Predicate toPredicate(Root<NameOfFeatureDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (category.getNameOfFeatureDigitals().isEmpty() && filter.getSearch().isEmpty())
			return null;
		addExludeFilter(root, query, cb);
		addFilterByName(root, query, cb);
		Predicate[] result = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(result));
	}

	private void addFilterByName(Root<NameOfFeatureDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getSearch().isEmpty()){
			Predicate predicate = cb.like(root.get("name"), filter.getSearch()+"%");
			predicates.add(predicate);
}
	}

	private void addExludeFilter(Root<NameOfFeatureDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!category.getNameOfFeatureDigitals().isEmpty()){
			List<Integer> ids = category.getNameOfFeatureDigitals().stream()
					.map(NameOfFeatureDigital::getId).collect(Collectors.toList());
			Predicate predicate = cb.not(root.get("id").in(ids));
			predicates.add(predicate);
}
	}

}
