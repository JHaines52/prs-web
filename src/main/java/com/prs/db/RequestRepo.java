package com.prs.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.model.Request;

public interface RequestRepo extends JpaRepository<Request, Integer>{
	List<Request> findByStatus(String status);
	List<Request> findByUserIdNotAndStatus(Integer userId, String status);

}
