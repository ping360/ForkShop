package ua.com.forkShop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="_name")
	private String name;
	
	@Column(name = "version", nullable = true)
	private Integer version;
	
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category")
	private Category category;
	
	@ManyToMany
	@JoinTable(name="digital_unit_item",
	joinColumns=@JoinColumn(name="id_item"),
	inverseJoinColumns=@JoinColumn(name="id_digital_unit"))
	private List<DigitalUnit> digitalUnits = new ArrayList<DigitalUnit>();
	
	@ManyToMany
	@JoinTable(name="item_feature_string",
			joinColumns=@JoinColumn(name="id_item"),
			inverseJoinColumns=@JoinColumn(name="id_feature_string"))
	private List<FeatureString> featureStrings = new ArrayList<FeatureString>();
	
	@ManyToMany
	@JoinTable(name="item_feature_digital",
			joinColumns=@JoinColumn(name="id_item"),
			inverseJoinColumns=@JoinColumn(name="id_feature_digital"))
	private List<FeatureDigital> featureDigitals = new ArrayList<FeatureDigital>();

	@ManyToMany(mappedBy="items")
	private List<ShopingCart> shopingCarts = new ArrayList<>();
	
	public List<ShopingCart> getShopingCarts() {
		return shopingCarts;
	}

	public void setShopingCarts(List<ShopingCart> shopingCarts) {
		this.shopingCarts = shopingCarts;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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

	public List<FeatureDigital> getFeatureDigitals() {
		return featureDigitals;
	}

	public void setFeatureDigitals(List<FeatureDigital> featureDigitals) {
		this.featureDigitals = featureDigitals;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
