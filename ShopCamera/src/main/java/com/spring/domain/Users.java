package com.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "Không được để trống")
	@Column(name = "name")
	private String name;

	// @Email(message = "Email không chính xác")
	@NotEmpty(message = "Không được để trống")
	@Column(name = "email")
	private String email;

	@NotEmpty(message = "Không được để trống")
//	@Size(min = 3, max = 20, message = "Mật khẩu quá ngắn")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "Không được để trống")
	@Column(name = "phone_number")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Số điện thoại không đúng")
	private String phone_number;

	@NotEmpty(message = "Không được để trống")
	@Column(name = "address")
	private String address;

	@Column(name = "id_role")
	private Integer id_role;

	@Column(name = "active")
	private Integer active;

	public Users(int id, String name, String email, String password, String phone_number, String address,
			Integer id_role, Integer active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.address = address;
		this.id_role = id_role;
		this.active = active;
	}

	public Users() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId_role() {
		return id_role;
	}

	public void setId_role(Integer id_role) {
		this.id_role = id_role;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone_number="
				+ phone_number + ", address=" + address + ", id_role=" + id_role + ", active=" + active + "]";
	}

}
