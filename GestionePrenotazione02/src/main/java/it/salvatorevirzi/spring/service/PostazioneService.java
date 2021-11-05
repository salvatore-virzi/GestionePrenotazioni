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

import it.salvatorevirzi.spring.model.Edificio;
import it.salvatorevirzi.spring.model.Postazione;
import it.salvatorevirzi.spring.model.TipoPostazione;
import it.salvatorevirzi.spring.repository.PostazioneRepository;

@Service
public class PostazioneService {
	@Autowired
	PostazioneRepository postazioneRepository;
	
	public List<Postazione> myFindAllPostazione() {
        return postazioneRepository.findAll();
    }
    
    public Postazione myFindPostazioneById(Long id) {
        return postazioneRepository.getById(id);
    }
    
    public Optional<Postazione> findPostazioneById(Long id) {
        return postazioneRepository.findById(id);
    }
    
    public Postazione findPostazioneCodice(String nome) {
    	return postazioneRepository.findPostazioneCodice(nome);
    }
    
    public List<Optional<Postazione>> findPostazioneByTipoCitta(TipoPostazione tipo, String citta){
    	return postazioneRepository.findPostazioneByTipoCitta(tipo, citta);    	
    }
    
    public void myInsertPostazione(String codice, String descrizione, Integer numeroMassimoOccupanti, TipoPostazione tipo,
			Edificio edificio) {
    	postazioneRepository.save(new Postazione(codice, descrizione,numeroMassimoOccupanti, tipo,
    		edificio));
    }
    
    public Page<Postazione> findAllPostazionePageable(Pageable pageable){
    	return postazioneRepository.findAll(pageable);
    }
    
	// Paginazione e Ordinamento
    public List<Postazione> findAllPostazionePageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Postazione> pagedResult = postazioneRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Postazione>();
        }}
    
    // Ordinamento
    public List<Postazione> findAllPostazioneSorted() {
        return postazioneRepository.findByOrderByCodiceAsc();
    }
}
