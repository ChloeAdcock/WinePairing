package com.qa.bae.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "wine")
public class Wine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wine_id")
	@EqualsAndHashCode.Include
	private Long id;
	
	@EqualsAndHashCode.Include
	private String name;
	@EqualsAndHashCode.Include
	private String grape;
	@EqualsAndHashCode.Include
	private String description;
	@EqualsAndHashCode.Include
	private String tastingNotes;
	@EqualsAndHashCode.Include
	private int likes;
	
    @OneToMany(mappedBy="wine")
    @EqualsAndHashCode.Include
    private Set<Food> food;
    
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

	


}
