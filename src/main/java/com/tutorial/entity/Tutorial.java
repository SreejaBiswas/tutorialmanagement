package com.tutorial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tute")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tid")
	private Long id;
	@Column(name = "ttitle")
	private String title;
	@Column(name = "tdesc")
	private String description;
	@Column(name = "tpub")
	private boolean published;

	public Tutorial(String title, String description, boolean published) {
		super();
		this.title = title;
		this.description = description;
		this.published = published;
	}

}
