package ua.com.forkShop.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.forkShop.dto.filter.BasicFilter;
import ua.com.forkShop.entity.DigitalUnit;

public class DigitalUnitSpecification implements Specification<DigitalUnit>{

	private final BasicFilter filter;

	public DigitalUnitSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<DigitalUnit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
return cb.like(root.get("name"), filter.getSearch()+"%");
}
}