package ua.com.forkShop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category", indexes=@Index(columnList = "_name"))
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="_name")
	private String name;
	
	@OneToMany(mappedBy="category")
	private List<Item> items = new ArrayList<Item>();
	
	@ManyToMany
	@JoinTable(name="category_name_of_feature_digital",
	joinColumns=@JoinColumn(name="id_category"),
	inverseJoinColumns=@JoinColumn(name="id_name_of_feature_digital"))
	private List<NameOfFeatureDigital> nameOfFeatureDigitals = new ArrayList<NameOfFeatureDigital>();

	@ManyToMany
	@JoinTable(name="category_name_of_feature_string",
	joinColumns=@JoinColumn(name="id_category"),
	inverseJoinColumns=@JoinColumn(name="id_name_of_feature_string"))
	private List<NameOfFeatureString> nameOfFeatureStrings = new ArrayList<NameOfFeatureString>();

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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<NameOfFeatureDigital> getNameOfFeatureDigitals() {
		return nameOfFeatureDigitals;
	}

	public void setNameOfFeatureDigitals(List<NameOfFeatureDigital> nameOfFeatureDigitals) {
		this.nameOfFeatureDigitals = nameOfFeatureDigitals;
	}

	public List<NameOfFeatureString> getNameOfFeatureStrings() {
		return nameOfFeatureStrings;
	}

	public void setNameOfFeatureStrings(List<NameOfFeatureString> nameOfFeatureStrings) {
		this.nameOfFeatureStrings = nameOfFeatureStrings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
