package it.salvatorevirzi.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.salvatorevirzi.spring.model.Citta;
import it.salvatorevirzi.spring.model.Edificio;
import it.salvatorevirzi.spring.repository.EdificioRepository;

@Service
public class EdificioService {
	@Autowired
	EdificioRepository edificioRepository;
	
	public List<Edificio> findAllEdificio() {
        return edificioRepository.findAll();
    }
	
	public Edificio findEdificioNome(String nome) {
		return edificioRepository.findEdificioByNome(nome);
	}
    
    public Optional<Edificio> findEdificioById(long id) {
        return edificioRepository.findById(id);
    }
    
    public void myInsertEdificio(String nome,String indirizzo, Citta citta, String codice){
    	edificioRepository.save(new Edificio(nome, indirizzo, citta, codice));
    }
    
    public Page<Edificio> findAllEdificioPageable(Pageable pageable){
    	return edificioRepository.findAll(pageable);
    }
    
    // Ordinamento
    public List<Edificio> findAllEdificioSorted() {
        return edificioRepository.findByOrderByNomeAsc();
    }

	// Paginazione e Ordinamento
    public List<Edificio> findAllEdificioPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Edificio> pagedResult = edificioRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Edificio>();
        }}
}
