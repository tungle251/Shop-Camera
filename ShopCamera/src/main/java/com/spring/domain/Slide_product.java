package com.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "slide_product")
public class Slide_product implements Serializable {

	@Id
	@Column(name = "id_slide")
	private int id_slide;

	@Id
	@Column(name = "id_product")
	private int id_product;

	public Slide_product() {
		super();
	}

	public int getId_slide() {
		return id_slide;
	}

	public void setId_slide(int id_slide) {
		this.id_slide = id_slide;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

}
