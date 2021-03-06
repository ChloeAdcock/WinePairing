package com.qa.bae.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "food")
@Entity
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Long id;
	
	private String name;
	private String allergens;
	private String description;
	private int likes;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wine_id", nullable=true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
    private Wine wine;
	
	public Food(String name, String allergens, String description, int likes) {
		super();
		this.name = name;
		this.allergens = allergens;
		this.description = description;
		this.likes = likes;
	}

	public Food() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAllergens() {
		return allergens;
	}

	public void setAllergens(String allergens) {
		this.allergens = allergens;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allergens == null) ? 0 : allergens.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + likes;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wine == null) ? 0 : wine.hashCode());
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
		Food other = (Food) obj;
		if (allergens == null) {
			if (other.allergens != null)
				return false;
		} 
		else if (!allergens.equals(other.allergens))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} 
		else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (likes != other.likes)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} 
		else if (!name.equals(other.name))
			return false;
		if (wine == null) {
			if (other.wine != null)
				return false;
		} 
		else if (!wine.equals(other.wine))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", allergens=" + allergens + ", description=" + description
				+ ", likes=" + likes + ", wine=" + wine + "]";
	}
}
