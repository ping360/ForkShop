package ua.com.forkShop.dto.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFilter {

	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

	private String search = "";

	private String maxPrice = "";

	private String minPrice = "";

	private Integer max;

	private Integer min;

	private List<Integer> brandIds = new ArrayList<>();

	private List<Integer> fsIds = new ArrayList<>();

	private List<FeatureDigitalFilter> featureDigitalFilter = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(PEATTERN.matcher(maxPrice).matches())max = Integer.valueOf(maxPrice);
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		if(PEATTERN.matcher(minPrice).matches())min = Integer.valueOf(minPrice);
		this.minPrice = minPrice;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public List<Integer> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Integer> brandIds) {
		this.brandIds = brandIds;
	}

	public List<Integer> getFsIds() {
		return fsIds;
	}

	public void setFsIds(List<Integer> fsIds) {
		this.fsIds = fsIds;
	}

	public List<FeatureDigitalFilter> getFeatureDigitalFilter() {
		return featureDigitalFilter;
	}

	public void setFeatureDigitalFilter(List<FeatureDigitalFilter> featureDigitalFilter) {
		this.featureDigitalFilter = featureDigitalFilter;
	}

	
}
