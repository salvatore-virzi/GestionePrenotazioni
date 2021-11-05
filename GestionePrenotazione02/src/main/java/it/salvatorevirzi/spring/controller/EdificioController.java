package it.salvatorevirzi.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import it.salvatorevirzi.spring.model.Edificio;
import it.salvatorevirzi.spring.service.CittaService;
import it.salvatorevirzi.spring.service.EdificioService;

@RestController
@RequestMapping("/edificiocontroller")
public class EdificioController {
	@Autowired
	private EdificioService edificioService;
	@Autowired
	private CittaService cittaService;
	
	@GetMapping("/edificioall")
	public List<Edificio> getEdificioAll(){
		List<Edificio> listEdificio= edificioService.findAllEdificio();
		return listEdificio;
	}
	
	@GetMapping("/edificionome/{nome}")
	public Edificio getEdificioNome(@PathVariable(required=true) String nome) {
		Edificio e = edificioService.findEdificioNome(nome);
		return e;
	}
	
	@GetMapping("/edificiosave")
	public void salvaEdificio(@RequestParam String nome, String indirzzo, String citta, String codice){
		if(codice.length() != 8) {
			ResponseEntity.badRequest();
		} else {
		edificioService.myInsertEdificio(nome, indirzzo, cittaService.findCittaNome(citta), codice);
			ResponseEntity.ok();
		}	
	}
	
	 @GetMapping(value = "/edificiopage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<Edificio>> getAllEdificioPage(Pageable pageable){
		 Page<Edificio> findAll = edificioService.findAllEdificioPageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } 
	 }
	 
	 @GetMapping("/edificiopageparametri")
	 public Page<Edificio> getEdificioPage(int page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Edificio> pageResult = edificioService.findAllEdificioPageable(paging);
		if(pageResult.hasContent()) {
			return pageResult;
		}else  {
			return null;
		}
	 }
	 
	 @GetMapping(value = "/edificiopagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Map<String, Object> getAllEdificioPageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
	        Page<Edificio> edificio = getEdificioPage(page, size);
	        Map<String, Object> response = new HashMap<>();
	        response.put("edificio", edificio);
	        return response;
	    }
	 
	    
	    @GetMapping(value = "/edificiopagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Edificio>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Edificio> list = edificioService.findAllEdificioPageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Edificio>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getalledificiosortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Edificio> getAllCittaSortByName() {
	        return edificioService.findAllEdificioSorted();
	    }
}