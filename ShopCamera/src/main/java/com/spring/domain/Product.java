package com.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author TungLe
 *
 */

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "id_category", nullable = false)
	// private Category category;
	@NotNull
	@Column(name = "id_category", nullable = false)
	private Integer id_category;

	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price")
	private Integer price;

	@Column(name = "eval")
	private Integer eval;

	@Column(name = "viewer")
	private Integer viewer;

	@Column(name = "status")
	private Integer status;

	@Column(name = "quatity")
	private Integer quatity;

	@Column(name = "img")
	private String img;

	@Column(name = "id_provider")
	private Integer id_provider;

	@Column(name = "detail_product")
	private String detail_product;

	@Column(name = "digital")
	private String digital;

	@Column(name = "link")
	private String link;

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// public Category getCategory() {
	// return category;
	// }
	//
	// public void setCategory(Category category) {
	// this.category = category;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getEval() {
		return eval;
	}

	public void setEval(Integer eval) {
		this.eval = eval;
	}

	public Integer getViewer() {
		return viewer;
	}

	public void setViewer(Integer viewer) {
		this.viewer = viewer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getQuatity() {
		return quatity;
	}

	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getId_provider() {
		return id_provider;
	}

	public void setId_provider(Integer id_provider) {
		this.id_provider = id_provider;
	}

	public String getDetail_product() {
		return detail_product;
	}

	public void setDetail_product(String detail_product) {
		this.detail_product = detail_product;
	}

	public String getDigital() {
		return digital;
	}

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}

	// @Override
	// public String toString() {
	// return "Product [id=" + id + ", category=" + category + ", name=" + name + ",
	// price=" + price + ", eval=" + eval
	// + ", viewer=" + viewer + ", status=" + status + ", quatity=" + quatity + ",
	// img=" + img
	// + ", id_provider=" + id_provider + ", detail_product=" + detail_product + ",
	// digital=" + digital
	// + ", link=" + link + "]";
	// }

}
