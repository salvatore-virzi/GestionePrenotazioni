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

import it.salvatorevirzi.spring.model.Citta;
import it.salvatorevirzi.spring.service.CittaService;

@RestController
@RequestMapping("/cittacontroller")
public class CittaController {
	@Autowired
	private CittaService cittaService;
	
	@GetMapping("/cittaall")
	public List<Citta> getAllCitta(){
		List<Citta> listCitta = cittaService.findAllCitta();
		return listCitta;
	}
	
	@GetMapping("/cittanome/{nome}")
	public Citta getCittaNome(@PathVariable(required=true) String nome) {
		Citta c = cittaService.findCittaNome(nome);
		return c;
	}
	
	@GetMapping("/cittasave")
	public void salvaCitta(@RequestParam String nome) {
		cittaService.insertCitta(nome);
	}
	
	 @GetMapping(value = "/cittapage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<Citta>> getAllCittaPage(Pageable pageable){
		 Page<Citta> findAll = cittaService.findAllCittaPageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } }
	 
	 
	 @GetMapping("/cittapageparametri")
	 public Page<Citta> getCittaPage(int page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Citta> pageResult = cittaService.findAllCittaPageable(paging);
		if(pageResult.hasContent()) {
			return pageResult;
		}else  {
			return null;
		}
	 }
	 
	 @GetMapping(value = "/cittapagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Map<String, Object> getAllCittaPageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
	        Page<Citta> citta = getCittaPage(page, size);
	        Map<String, Object> response = new HashMap<>();
	        response.put("citta", citta);
	        return response;
	    }
	 
	    
	    @GetMapping(value = "/cittapagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Citta>> myGetAllCittaPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Citta> list = cittaService.findAllCittaPageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Citta>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getallcittasortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Citta> getAllCittaSortByName() {
	        return cittaService.findAllCittaSorted();
	    }
			 
	 
}
