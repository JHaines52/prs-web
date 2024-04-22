package com.prs.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.RequestRepo;
import com.prs.model.Product;
import com.prs.model.Request;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")

public class RequestController {
	@Autowired // Initializes create variable
	private RequestRepo requestRepo;
	private String statusApproved = "APPROVED";
	private String statusReview = "REVIEW";
	private String statusRejected = "REJECTED";
	private String statusNew = "NEW";

	@GetMapping("/")
	public List<Request> getAllRequests() {
		return requestRepo.findAll();
	}

	@GetMapping("/{id}")
	public Request getRequestById(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			return r.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request Not Found");
		}

	}

	@GetMapping("/reviews/{id}") // the user id is being passed in
	public List<Request> getRequestInReview(@PathVariable int id) {

		List<Request> reviews = requestRepo.findByUserIdNotAndStatus(id, statusReview);

		if (reviews.isEmpty()) {
			System.err.println("Request reviews not found.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Not Found"); // Return Not Found
		}

		return reviews; // Return the list of review requests
	}

	@PostMapping("")
	public Request addRequest(@RequestBody Request request) {
		request.setStatus(statusNew);
		//total 0
		request.setTotal(0);
		//current dates
		request.setSubmittedDate(LocalDate.now());
		return requestRepo.save(request);
	}
	

	@PostMapping("/review/{id}")
	public Request reviewStatusRequest(@PathVariable int id) {
		Request request = requestRepo.findById(id).get();

		if (request.getTotal() <= 50) {
			request.setStatus(statusApproved);
		} else {
			request.setStatus(statusReview);
		}

		return requestRepo.save(request);
	}

	@PostMapping("/reject/{id}")
	public Request rejectRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);

		if (!r.isPresent()) {
			System.err.println("Request not rejected.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please try again");
		}
		Request request = r.get();

		request.setStatus(statusRejected);

		return requestRepo.save(request);
	}

	@PostMapping("/approve/{id}")
	public Request approveRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);

		if (!r.isPresent()) {
			System.err.println("Request not approved.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please try again");
		}
		Request request = requestRepo.findById(id).get();
		request.setStatus(statusApproved);

		return requestRepo.save(request);
	}

	@PutMapping("/{id}")
	public Request updateRequest(@PathVariable int id, @RequestBody Request request) {

		Request r = null;

		if (id != request.getId()) {
			System.err.println("Request id does not match path id.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Not Found");
		} else if (!requestRepo.existsById(id)) {
			System.err.println("Request does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request Not Found");
		} else {
			try {
				r = requestRepo.save(request);
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		}
		return r;
	}

	@DeleteMapping("/{id}")
	public boolean deleteRequest(@PathVariable int id) {
		boolean success = false;
		if (requestRepo.existsById(id)) {
			requestRepo.deleteById(id);
			success = true;
		} else {
			System.err.println("Delete Error: No request exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request Not Found");
		}
		return success;
	}

}
