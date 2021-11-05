package it.salvatorevirzi.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.salvatorevirzi.spring.model.Role;
import it.salvatorevirzi.spring.model.RoleType;

@Component
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Page<Role> findAll(Pageable pageble);
	
	 public List<Role> findByOrderByIdAsc();

	 Optional<Role> findByRoleType(RoleType roletype);
		
}
