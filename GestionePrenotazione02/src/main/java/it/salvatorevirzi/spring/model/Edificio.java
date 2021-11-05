package it.salvatorevirzi.spring.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import it.salvatorevirzi.spring.security.StringAttributeConverter;

@Entity
@Table(name="edificio")
public class Edificio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String nome;
	private String indirizzo;
	@ManyToOne
	private Citta citta;
	@Column(columnDefinition="TEXT")
	@Length(min = 8, max = 8)
    @Convert(converter = StringAttributeConverter.class)
    private String codice;

	public Edificio() {}
	
	public Edificio(String nome, String indirizzo, Citta citta, String codice) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.codice=codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Citta getCitta() {
		return citta;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	@Override
	public String toString() {
		return "Edificio [id=" + id + ", nome=" + nome + ", indirizzo=" + indirizzo + ", citta=" + citta.getNome() + "]";
	}
}
