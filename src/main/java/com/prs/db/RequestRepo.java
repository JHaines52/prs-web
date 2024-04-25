package com.prs.db;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.model.LineItem;
import com.prs.model.Request;

public interface RequestRepo extends JpaRepository<Request, Integer>{
	List<Request> findByStatus(String status);
	List<Request> findByUserIdNotAndStatus(Integer userid, String status);
	List<Request> findByUserId(int userId);
	
	
//	Optional<Request> findByDescriptionAndDateneeded(String description, LocalDate dateNeeded);
}
