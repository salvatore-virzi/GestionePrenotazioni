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

import it.salvatorevirzi.spring.model.Role;
import it.salvatorevirzi.spring.model.RoleType;
import it.salvatorevirzi.spring.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	
	public List<Role> myFindAllUsers() {
        return roleRepository.findAll();
    }
    
    public Optional<Role> myFindUserById(long id) {
        return roleRepository.findById(id);
    }
    
    public void myInsertRole(RoleType roletype) {
    	roleRepository.save(new Role(roletype));
    }
    
    public Page<Role> findAllRolePageable(Pageable pageable){
    	return roleRepository.findAll(pageable);
    }
    
    // Ordinamento
    public List<Role> findAllRoleSorted() {
        return roleRepository.findByOrderByIdAsc();
    }

	// Paginazione e Ordinamento
    public List<Role> findAllRolePageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Role> pagedResult = roleRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Role>();
        }}
}
