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
import ua.com.forkShop.entity.FeatureDigital;
import ua.com.forkShop.entity.FeatureString;
import ua.com.forkShop.entity.Item;

public class ItemSpecification implements Specification<Item> {

	private final ItemFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	public ItemSpecification(ItemFilter filter) {
		this.filter = filter;
	}

	private void filterByFeatureDigital(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		for (FeatureDigitalFilter digitalFilter : filter.getFeatureDigitalFilter()) {
			DigitalSpecification specification = new DigitalSpecification(digitalFilter);
			Predicate predicate = specification.toPredicate(root, query, cb);
			if (predicate != null) {
				predicates.add(predicate);
			}
		}
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

	private class DigitalSpecification implements Specification<Item> {

		private final FeatureDigitalFilter digitalFilter;

		private final List<Predicate> predicatesDigital = new ArrayList<>();

		public DigitalSpecification(FeatureDigitalFilter digitalFilter) {
			this.digitalFilter = digitalFilter;
		}

		private void filterByValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			if (digitalFilter.getMaxValue() != null && digitalFilter.getMinValue() != null) {
				filterByMinMaxValue(root, query, cb);
			} else if (digitalFilter.getMaxValue() != null) {
				filterByMaxValue(root, query, cb);
			} else if (digitalFilter.getMinValue() != null) {
				filterByMinValue(root, query, cb);
			}
		}

		private void filterByMinValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			filterByNameId(root, query, cb);
			filterByDuId(root, query, cb);
			Join<Item, FeatureDigital> join = root.join("featureDigitals");
			predicatesDigital.add(cb.greaterThanOrEqualTo(join.get("value"), digitalFilter.getMinValue()));
		}

		private void filterByMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			filterByNameId(root, query, cb);
			filterByDuId(root, query, cb);
			Join<Item, FeatureDigital> join = root.join("featureDigitals");
			predicatesDigital.add(cb.lessThanOrEqualTo(join.get("value"), digitalFilter.getMaxValue()));
		}

		private void filterByMinMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			filterByNameId(root, query, cb);
			filterByDuId(root, query, cb);
			Join<Item, FeatureDigital> join = root.join("featureDigitals");
			predicatesDigital
					.add(cb.between(join.get("value"), digitalFilter.getMinValue(), digitalFilter.getMaxValue()));
		}

		private void filterByDuId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			if (digitalFilter.getDuId() != null) {
				Join<Item, FeatureDigital> join = root.join("featureDigitals");
				Join<FeatureDigital, DigitalUnit> joinMs = join.join("digitalUnits");
				predicatesDigital.add(cb.equal(joinMs.get("id"), digitalFilter.getDuId()));
			}
		}

		private void filterByNameId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			if (digitalFilter.getNameId() != null) {
				Join<Item, FeatureDigital> join = root.join("featureDigitals");
				predicatesDigital.add(cb.equal(join.get("nameOfFeatureDigital"), digitalFilter.getNameId()));
			}
		}

		@Override
		public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			filterByValue(root, query, cb);
			if (predicatesDigital.isEmpty())
				return null;
			Predicate[] array = new Predicate[predicatesDigital.size()];
			predicatesDigital.toArray(array);
			return cb.and(array);
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
		filterByFeatureDigital(root, query, cb);
		if (predicates.isEmpty())
			return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);

	}

}
