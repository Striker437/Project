package com.smart.Entities;


import javax.persistence.*;


@Table(name="CONTACT")
@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String name;
	/*
	 * @Override public String toString() { return "Contact [cId=" + cId + ", name="
	 * + name + ", secondName=" + secondName + ", email=" + email + ", work=" + work
	 * + ", phone=" + phone + ", description=" + description + ", image=" + image +
	 * ", user=" + user + "]"; }
	 */
	private String secondName;
	private String email;
	private String work;
	private String phone;
	@Column(length =1000 )
	private String description;
	private String image;
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	

}
