package ua.com.forkShop.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feature_string", indexes=@Index(columnList = "_name"))
public class FeatureString {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="_name")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_name_of_feature_string")
	private NameOfFeatureString nameOfFeatureString;
	
	@ManyToMany(mappedBy="featureStrings")
	private List<Item> items = new ArrayList<Item>();

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

	public NameOfFeatureString getNameOfFeatureString() {
		return nameOfFeatureString;
	}

	public void setNameOfFeatureString(NameOfFeatureString nameOfFeatureString) {
		this.nameOfFeatureString = nameOfFeatureString;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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
		FeatureString other = (FeatureString) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
