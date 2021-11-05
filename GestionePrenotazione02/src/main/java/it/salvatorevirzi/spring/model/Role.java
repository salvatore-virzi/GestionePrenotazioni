package it.salvatorevirzi.spring.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoletype(RoleType roletype) {
		this.roleType = roletype;
	}

	public Role() {}
	public Role(RoleType roletype) {
		this.roleType = roletype;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roletype=" + roleType + "]";
	}
}
