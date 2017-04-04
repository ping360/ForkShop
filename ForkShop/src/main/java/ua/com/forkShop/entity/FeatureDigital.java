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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "feature_digital", indexes=@Index(columnList = "_value"))
public class FeatureDigital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="_value")
	private BigDecimal value;
	
	@ManyToMany(mappedBy="featureDigitals")
	private List<Item> items = new ArrayList<Item>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_name_of_feature_digital")
	private NameOfFeatureDigital nameOfFeatureDigital;
	
	@ManyToMany
	@JoinTable(name = "digital_unit_feature_digital",
	joinColumns=@JoinColumn(name="id_feature_digital"),
	inverseJoinColumns=@JoinColumn(name="id_digital_unit"))
	@BatchSize(size = 20)
	private List<DigitalUnit> digitalUnits = new ArrayList<DigitalUnit>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public NameOfFeatureDigital getNameOfFeatureDigital() {
		return nameOfFeatureDigital;
	}

	public void setNameOfFeatureDigital(NameOfFeatureDigital nameOfFeatureDigital) {
		this.nameOfFeatureDigital = nameOfFeatureDigital;
	}

	public List<DigitalUnit> getDigitalUnits() {
		return digitalUnits;
	}

	public void setDigitalUnits(List<DigitalUnit> digitalUnits) {
		this.digitalUnits = digitalUnits;
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
		FeatureDigital other = (FeatureDigital) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
