package com.prs.db;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.prs.model.Request;

public interface RequestRepo extends JpaRepository<Request, Integer>{
	List<Request> findByStatus(String status);
	List<Request> findByUserIdNotAndStatus(Integer userid, String status);
	
//	Optional<Request> findByDescriptionAndDateneeded(String description, LocalDate dateNeeded);
}
