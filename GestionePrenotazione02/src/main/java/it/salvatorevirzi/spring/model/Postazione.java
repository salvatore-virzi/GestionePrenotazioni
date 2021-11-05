package it.salvatorevirzi.spring.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "postazione")
public class Postazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codice;
	private String descrizione;
	private Integer numeroMassimoOccupanti;
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	@ManyToOne
	private Edificio edificio;

	public Postazione() {
	}

	public Postazione(String codice, String descrizione, Integer numeroMassimoOccupanti, TipoPostazione tipo,
			Edificio edificio) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.numeroMassimoOccupanti = numeroMassimoOccupanti;
		this.tipo = tipo;
		this.edificio = edificio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getNumeroMassimoOccupanti() {
		return numeroMassimoOccupanti;
	}

	public void setNumeroMassimoOccupanti(Integer numeroMassimoOccupanti) {
		this.numeroMassimoOccupanti = numeroMassimoOccupanti;
	}

	public TipoPostazione getTipo() {
		return tipo;
	}

	public void setTipo(TipoPostazione tipo) {
		this.tipo = tipo;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	@Override
	public String toString() {
		return "Postazione [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione
				+ ", numeroMassimoOccupanti=" + numeroMassimoOccupanti + ", tipo=" + tipo + ", edificio=" + edificio.getNome()
				+ "]";
	}

}
