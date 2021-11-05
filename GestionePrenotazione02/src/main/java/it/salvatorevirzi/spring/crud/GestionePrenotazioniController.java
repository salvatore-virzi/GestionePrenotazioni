package it.salvatorevirzi.spring.crud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class GestionePrenotazioniController {
	
	@Value("${gestioneprenotazioni.istruzioniInglese}")
	private String istruzioniInglese;
	
	@Value("${gestioneprenotazioni.istruzioniItaliano}")
	private String istruzioniItaliano;
	
	@GetMapping("/regole")
	String readRegole(@RequestParam String language) {
		if(language.equalsIgnoreCase("en") ) {
			return istruzioniInglese;
		}else if (language.equalsIgnoreCase("it") ) {
			return istruzioniItaliano;
		} else {
			return "ERROR\nLingua non supportata";
		}		
	}	
}
