package it.salvatorevirzi.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import it.salvatorevirzi.spring.model.User;
import it.salvatorevirzi.spring.service.UserService;

@RestController
@RequestMapping("/usercontroller")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/userall")
	public List<User> fingUserAll(){
		List<User> listUser = userService.myFindAllUsers();
		return listUser;
	}
	
	@GetMapping("/userusername/{username}")
	public User getUserByUsername(@PathVariable(required = true) String username) {
		return userService.findUserByUsername(username);
	}
	
	@GetMapping("/usersave")
	public void salvaUser(String usurname, String nome, String cognome, String email, String password, Boolean active) {
		userService.myInsertUser(usurname, nome, cognome, email, password, active);
	}
	
	 @GetMapping(value = "/userpage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<User>> getAllUserPage(Pageable pageable){
		 Page<User> findAll = userService.findAllUserPageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }}
	 
	    @GetMapping(value = "/userpagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<User>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<User> list = userService.findAllUserPageSizeSort(page, size, sort);
	      return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getallusersortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<User> getAllCittaSortByName() {
	        return userService.findAllUserSorted();
	    } 
}
