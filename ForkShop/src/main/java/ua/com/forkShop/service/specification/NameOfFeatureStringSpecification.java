package ua.com.forkShop.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.NameOfFeatureString;

public class NameOfFeatureStringSpecification implements Specification<NameOfFeatureString> {

	private final BasicFilter filter;

	public NameOfFeatureStringSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<NameOfFeatureString> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("name"), filter.getSearch()+"%");
	}
	
}
