package ua.com.forkShop.dto.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.entity.Category;
import ua.com.forkShop.entity.DigitalUnit;
import ua.com.forkShop.entity.FeatureString;

public class ItemForm {

	private int id;

	private String name;

	private String price;

	private Brand brand;

	private Category category;

	private List<DigitalUnit> digitalUnits = new ArrayList<>();

	private List<FeatureString> featureStrings = new ArrayList<>();

	private List<FeatureDigitalForm> featureDigitals = new ArrayList<>();

	private MultipartFile file;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<DigitalUnit> getDigitalUnits() {
		return digitalUnits;
	}

	public void setDigitalUnits(List<DigitalUnit> digitalUnits) {
		this.digitalUnits = digitalUnits;
	}

	public List<FeatureString> getFeatureStrings() {
		return featureStrings;
	}

	public void setFeatureStrings(List<FeatureString> featureStrings) {
		this.featureStrings = featureStrings;
	}

	public List<FeatureDigitalForm> getFeatureDigitals() {
		return featureDigitals;
	}

	public void setFeatureDigitals(List<FeatureDigitalForm> featureDigitals) {
		this.featureDigitals = featureDigitals;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
