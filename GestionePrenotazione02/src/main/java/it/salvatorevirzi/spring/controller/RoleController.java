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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.salvatorevirzi.spring.model.Role;
import it.salvatorevirzi.spring.service.RoleService;

@RestController
@RequestMapping("/rolecontroller")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	 @GetMapping(value = "/rolepage", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Page<Role>> getAllRolePage(Pageable pageable){
		 Page<Role> findAll = roleService.findAllRolePageable(pageable);
		  if (findAll.hasContent()) {
			  return new ResponseEntity<>(findAll, HttpStatus.OK);
		  }else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } }
	 
	    @GetMapping(value = "/rolepagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Role>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Role> list = roleService.findAllRolePageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Role>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	    @GetMapping(value = "/getrolesortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Role> getAllCittaSortByName() {
	        return roleService.findAllRoleSorted();
	    } 
	
}
