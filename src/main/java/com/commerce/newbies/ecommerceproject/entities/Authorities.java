package com.commerce.newbies.ecommerceproject.entities;


//@Entity
public class Authorities {

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String role;
	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Authorities(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Authorities [id=" + id + ", role=" + role + "]";
	}
	
	
	
}
