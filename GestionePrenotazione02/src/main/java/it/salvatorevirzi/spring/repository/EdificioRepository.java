package it.salvatorevirzi.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.salvatorevirzi.spring.model.Edificio;

@Component
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
	@Query("SELECT e FROM Edificio e WHERE e.nome = :nome")
	public Edificio findEdificioByNome(String nome);
	
	public Page<Edificio> findAll(Pageable pageble);
	
	 public List<Edificio> findByOrderByNomeAsc();
}
