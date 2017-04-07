package ua.com.forkShop.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.forkShop.dto.filter.FeatureDigitalFilter;
import ua.com.forkShop.dto.filter.ItemFilter;
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.entity.Item;

public class ItemSpecification implements Specification<Item> {

	private final ItemFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	public ItemSpecification(ItemFilter filter) {
		this.filter = filter;
	}

	private void filterByFeatureString(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!filter.getFsIds().isEmpty()) {
			Join<Item, FeatureString> join = root.join("featureStrings");
			predicates.add(join.get("id").in(filter.getFsIds()));
		}
	}

	private void filterByBrand(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!filter.getBrandIds().isEmpty()) {
			predicates.add(root.get("brand").in(filter.getBrandIds()));
		}
	}
	
	private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (filter.getMax() != null && filter.getMin() != null) {
			predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
		} else if (filter.getMax() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
		} else if (filter.getMin() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
		}
	}

	private void filterBySearch(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!filter.getSearch().isEmpty()) {
			predicates.add(cb.like(root.get("name"), filter.getSearch() + "%"));
		}
	}


	private void fetch(Root<Item> root, CriteriaQuery<?> query) {
		if (query.getResultType() != Long.class) {
			root.fetch("category", JoinType.LEFT);
			root.fetch("brand", JoinType.LEFT);
		}
	}

	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterBySearch(root, query, cb);
		filterByPrice(root, query, cb);
		filterByFeatureString(root, query, cb);
		filterByBrand(root, query, cb);
		if (predicates.isEmpty())
			return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);

	}

}
