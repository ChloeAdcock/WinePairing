package com.qa.bae.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wine")
@Entity
public class Wine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wine_id")
	private Long id;
	private String name;
	private String grape;
	private String description;
	private String tastingNotes;
	private int likes;
	
	public Wine() {
		super();
	}

	public Wine(String name, String grape, String description, String tastingNotes, int likes) {
		super();
		this.name = name;
		this.grape = grape;
		this.description = description;
		this.tastingNotes = tastingNotes;
		this.likes = likes;
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

	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
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
		} 
		else if (!description.equals(other.description))
			return false;
		if (grape == null) {
			if (other.grape != null)
				return false;
		} 
		else if (!grape.equals(other.grape))
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
		if (tastingNotes == null) {
			if (other.tastingNotes != null)
				return false;
		} 
		else if (!tastingNotes.equals(other.tastingNotes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", name=" + name + ", grape=" + grape + ", description=" + description
				+ ", tastingNotes=" + tastingNotes + ", likes=" + likes + "]";
	}
}
