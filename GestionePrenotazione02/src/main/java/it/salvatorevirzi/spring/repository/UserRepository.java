package it.salvatorevirzi.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.salvatorevirzi.spring.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
		
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findUserUsername(String username);
	
	
	@Query("SELECT u FROM User u WHERE u.password = :password OR u.username =:username")
	public User booleanUsernamePassword(String password, String username);
	
	public Page<User> findAll(Pageable pageble);
	
	Optional<User> findByUsername(String username);
	
	 public List<User> findByOrderByUsernameAsc();


		Boolean existsByUsername(String username);
	    Boolean existsByEmail(String email);
}
