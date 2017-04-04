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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="name_of_feature_string", indexes=@Index(columnList = "_name"))
public class NameOfFeatureString {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="_name")
	private String name;
	
	@OneToMany(mappedBy="nameOfFeatureString")
	private List<FeatureString> featureStrings;
	
	@ManyToMany(mappedBy="nameOfFeatureStrings")
	private List<Category> categories = new ArrayList<Category>();

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

	public List<FeatureString> getFeatureStrings() {
		return featureStrings;
	}

	public void setFeatureStrings(List<FeatureString> featureStrings) {
		this.featureStrings = featureStrings;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
		NameOfFeatureString other = (NameOfFeatureString) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
