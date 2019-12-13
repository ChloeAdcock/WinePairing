package com.qa.bae.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "wine")
public class Wine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wine_id")
	private Long id;
	
	private String name;
	private String grape;
	private String description;
	private String tastingNotes;
	private String likes;
	
	@ManyToMany
	@JoinTable(
			name = "foods_and_wines",
	        joinColumns = @JoinColumn(name = "food_id"),
	        inverseJoinColumns = @JoinColumn(name = "wine_id"))
	Set <Food> foodPairing;

	public Wine() {
		super();
	}

	public Wine(String name, String grape, String description, String tastingNotes, String likes) {
		super();
		this.name = name;
		this.grape = grape;
		this.description = description;
		this.tastingNotes = tastingNotes;
		this.likes = likes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrape() {
		return grape;
	}

	public void setGrape(String grape) {
		this.grape = grape;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTastingNotes() {
		return tastingNotes;
	}

	public void setTastingNotes(String tastingNotes) {
		this.tastingNotes = tastingNotes;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", name=" + name + ", grape=" + grape + ", description=" + description
				+ ", tastingNotes=" + tastingNotes + ", likes=" + likes + ", foodPairing=" + foodPairing + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((foodPairing == null) ? 0 : foodPairing.hashCode());
		result = prime * result + ((grape == null) ? 0 : grape.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tastingNotes == null) ? 0 : tastingNotes.hashCode());
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
		Wine other = (Wine) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (foodPairing == null) {
			if (other.foodPairing != null)
				return false;
		} else if (!foodPairing.equals(other.foodPairing))
			return false;
		if (grape == null) {
			if (other.grape != null)
				return false;
		} else if (!grape.equals(other.grape))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tastingNotes == null) {
			if (other.tastingNotes != null)
				return false;
		} else if (!tastingNotes.equals(other.tastingNotes))
			return false;
		return true;
	}
}
