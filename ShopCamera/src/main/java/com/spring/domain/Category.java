package com.spring.domain;

//import java.util.ArrayList;
//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "id_root")
	private int id_root;

	@Column(name = "status")
	private Integer status;

	@Column(name = "menu")
	private Integer menu;

	@Column(name = "icon")
	private String icon;

	@Column(name = "link")
	private String link;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "category")
	// private List<Product> listProduct = new ArrayList<>();

	// public List<Product> getListProduct() {
	// return listProduct;
	// }
	//
	// public void setListProduct(List<Product> listProduct) {
	// this.listProduct = listProduct;
	// }

	public Category() {
		super();
	}

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

	public int getId_root() {
		return id_root;
	}

	public void setId_root(int id_root) {
		this.id_root = id_root;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMenu() {
		return menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", id_root=" + id_root + ", status=" + status + ", menu="
				+ menu + ", icon=" + icon + ", link=" + link + "]";
	}

}
