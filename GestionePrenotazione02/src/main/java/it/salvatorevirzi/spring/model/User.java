package it.salvatorevirzi.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import it.salvatorevirzi.spring.security.StringAttributeConverter;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	//unique = true rende un username univoco. In questo modo non è possibile avere due username uguali
	@Column(unique = true)
	private String username;
	private String nome;
    private String cognome; 
    @Convert(converter = StringAttributeConverter.class)
	private String email;

	private String password;
	private Boolean active = true;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	public User() {}
	
    public User(Long id, String username, @Email String email, String password, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }
    
	
	public User(String usurname, String nome, String cognome, String email, String password, Boolean active) {
		this.username = usurname;
		this.nome = nome;
		this.cognome= cognome;
		this.email = email;
		this.password = password;
		this.active = active;
		}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usurname) {
		this.username = usurname;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", usurname=" + username + ", nome=" + nome + ", email=" + email + ", password="
				+ password + ", active=" + active + ", roles=" + roles.toString() + "]";
	}
}
