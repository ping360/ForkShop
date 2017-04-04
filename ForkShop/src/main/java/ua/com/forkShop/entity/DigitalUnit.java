package ua.com.forkShop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "digital_unit", indexes=@Index(columnList = "_name"))
public class DigitalUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_name")
	private String name;
	
	@ManyToMany(mappedBy="digitalUnits")
	private List<Item> items = new ArrayList<Item>();
	
	@ManyToMany(mappedBy="digitalUnits")
	private List<FeatureDigital> featureDigitals = new ArrayList<FeatureDigital>();

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

	public List<FeatureDigital> getFeatureDigitals() {
		return featureDigitals;
	}

	public void setFeatureDigitals(List<FeatureDigital> featureDigitals) {
		this.featureDigitals = featureDigitals;
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
		DigitalUnit other = (DigitalUnit) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
