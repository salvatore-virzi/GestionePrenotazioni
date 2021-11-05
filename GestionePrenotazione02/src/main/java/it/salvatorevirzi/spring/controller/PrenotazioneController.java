package it.salvatorevirzi.spring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.salvatorevirzi.spring.model.Prenotazione;
import it.salvatorevirzi.spring.service.PostazioneService;
import it.salvatorevirzi.spring.service.PrenotazioneService;
import it.salvatorevirzi.spring.service.UserService;

@RestController
@RequestMapping("/prenotazionecontroller")
public class PrenotazioneController {
	@Autowired
	private PrenotazioneService prenotazioneService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostazioneService postazioneService;

	@GetMapping("/creaprenotazione")
	public String creaPrenotazione(@RequestParam Long userId, Long postazioneId, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPrenotata) {
		if(prenotazioneService.diffGiorni(dataPrenotata) && prenotazioneService.listaPrenotazioniUserDate(userId, dataPrenotata)) {
			prenotazioneService.insertPrenotazione(userService.myFindUserById(userId), postazioneService.myFindPostazioneById(postazioneId), dataPrenotata);
		System.out.println("Prenotazione inserita correttamente!");
		return "<h2><i>Prenotazione inserita correttamente!</i></h2><br><br><br><a href=\"http://localhost:8080/\"><button>Nuova Prenotazione</button></a>";
		} else {
			System.out.println("******* Errore\nScegliere una nuova data");
			return "<h2>ERRORE DATA NON DISPONIBILE</h2><br><br><a href=\"http://localhost:8080/\"><i>Scegliere una nuova data</i></a>";
		}
	//LINK: http://localhost:8080/prenotazionecontroller/creaprenotazione?userId=2&postazioneId=1&dataPrenotata=22-11-2021
	}	
	
	@GetMapping("/getprenotazioneall")
	public List<Prenotazione> getPrenotazioneAll() {
		List<Prenotazione> listPrenotazione= prenotazioneService.findAllPrenotazione();
		return listPrenotazione;
		}	
	
	@GetMapping("/prenotazionebyid/{id}")
	public Optional<Prenotazione> getPrenotazioneId(@PathVariable(required = true) long id){
		Optional<Prenotazione> p = prenotazioneService.findprenotazioneId(id);
		return p;
	}
	
	 @GetMapping(value = "/prenotazionepage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<Prenotazione>> getAllPrenotazionePage(Pageable pageable){
		 Page<Prenotazione> findAll = prenotazioneService.findAllPrenotazionePageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } }
	
	    
	    @GetMapping(value = "/prenotazionepagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Prenotazione>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Prenotazione> list = prenotazioneService.findAllPrenotazionePageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Prenotazione>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getallprenotazionesortbyid", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Prenotazione> getAllPrenotazioneSortById() {
	        return prenotazioneService.findAllPrenotazioneSorted();
	    } 
	
	
}