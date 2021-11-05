package it.salvatorevirzi.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.salvatorevirzi.spring.model.Postazione;
import it.salvatorevirzi.spring.model.TipoPostazione;
import it.salvatorevirzi.spring.service.EdificioService;
import it.salvatorevirzi.spring.service.PostazioneService;

@RestController
@RequestMapping("/postazionecontroller")
public class PostazioneController {
	@Autowired
	private PostazioneService postazioneService;
	@Autowired
	private EdificioService edificioService;
	
	@GetMapping("/postazione-tipo-citta")
	public List<Optional<Postazione>> postazioneTipoCitta(@RequestParam TipoPostazione tipo, String citta) {
		return postazioneService.findPostazioneByTipoCitta(tipo, citta);
		//LINK: http://localhost:8080/postazionecontroller/postazione-tipo-citta?tipo=OPENSPACE&citta=Roma
		//Tipo =  PRIVATO   OPENSPACE   SALA_RIUNIONI
	}
	
	@GetMapping("/postazioneAll")
	public List<Postazione> getPostazioneAll(){
		List<Postazione> listPostazione = postazioneService.myFindAllPostazione();
		return listPostazione;
	}
	
	@GetMapping("/postazioneid/{id}")
	public Optional<Postazione> getPostazioneId(@PathVariable(required = true) Long id){
		return postazioneService.findPostazioneById(id);
	}
	
	@GetMapping("/postazionesave")
	public void salvaPostazione(@RequestParam String codice, String descrizione, Integer numeroMassimoOccupanti, TipoPostazione tipo, String edificio) {
		postazioneService.myInsertPostazione(codice, descrizione, numeroMassimoOccupanti, tipo, edificioService.findEdificioNome(edificio));
	}
	
	 @GetMapping(value = "/postazionepage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<Postazione>> getAllPostazionePage(Pageable pageable){
		 Page<Postazione> findAll = postazioneService.findAllPostazionePageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } }
	
	 @GetMapping("/postazionepageparametri")
	 public Page<Postazione> getPostazionePage(int page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Postazione> pageResult = postazioneService.findAllPostazionePageable(paging);
		if(pageResult.hasContent()) {
			return pageResult;
		}else  {
			return null;
		}
	 }
	 
	 @GetMapping(value = "/postazionepagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Map<String, Object> getAllPostazionePageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
	        Page<Postazione> postazione = getPostazionePage(page, size);
	        Map<String, Object> response = new HashMap<>();
	        response.put("postazione", postazione);
	        return response;
	    }
	 
	    
	    @GetMapping(value = "/postazionepagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Postazione>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Postazione> list = postazioneService.findAllPostazionePageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Postazione>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getallpostazionesortbycodice", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Postazione> getAllPostazioneSortByCodice() {
	        return postazioneService.findAllPostazioneSorted();
	    } 
}
