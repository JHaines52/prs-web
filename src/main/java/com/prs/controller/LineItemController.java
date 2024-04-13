package com.prs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;
import com.prs.model.LineItem;
import com.prs.model.Request;

import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/api/lineitems")

public class LineItemController {

	@Autowired // Initializes create variable
	private LineItemRepo lineitemRepo;

	@Autowired
	private RequestRepo requestRepo;

	@Transactional
	private void recalculateRequestTotal(Request request) {
		// Fetch all LineItems associated with the given Request
		List<LineItem> lineitems = lineitemRepo.findByRequestId(request.getId());

		// Calculate the total
		double total = lineitems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();

		// Set the new total and save the Request
		request.setTotal((float) total);
		requestRepo.save(request);
	}

	@GetMapping("/line-for-prod/{requestId}")
	public List<LineItem> getLinesForRequest(@PathVariable int requestId) {
		List<LineItem> lineItems = lineitemRepo.findByRequestId(requestId);

		if (lineItems.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lineitem Not Found"); // Return 404 Not Found if
																							// there are no line items
		}

		return lineItems; // Return 200 OK with line items in the body
	}

	@GetMapping("/{id}")
	public LineItem getLineitemById(@PathVariable int id) {
		Optional<LineItem> item = lineitemRepo.findById(id);
		if (item.isPresent()) {
			return item.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lineitem Not Found");
		}
	}

	// Method to add a new Lineitem
	@PostMapping("")
	public LineItem addLineitem(@RequestBody LineItem lineitem) {

		Request request = lineitem.getRequest();
		lineitemRepo.save(lineitem);
		recalculateRequestTotal(request);
		return lineitem;

	}

	@PostMapping("/calculatetotal/{id}")
	public float updateTotal(@PathVariable int id) {
		LineItem lineitem = lineitemRepo.findById(id).get();
		Request request = lineitem.getRequest();
		recalculateRequestTotal(request);
		float newTotal = request.getTotal();
		return newTotal;
	}

	@PutMapping("/{id}")
	public LineItem updateLineitem(@PathVariable int id, @RequestBody LineItem lineitem) {

		LineItem item = null;
		Request request = lineitem.getRequest();

		if (id != lineitem.getId()) {
			System.err.println("Lineitem id does not match path id.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lineitem Not Found");

		} else if (!lineitemRepo.existsById(id)) {
			System.err.println("Lineitem does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
		} else {
			try {
				item = lineitemRepo.save(lineitem);
				recalculateRequestTotal(request);
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		}
		
		return item;
	}

	@DeleteMapping("/{id}")
	public boolean deleteLineitem(@PathVariable int id) {
		boolean success = false;

		if (lineitemRepo.existsById(id)) {
			LineItem lineitem = lineitemRepo.findById(id).get();
			Request request = lineitem.getRequest();
			lineitemRepo.delete(lineitem);
			recalculateRequestTotal(request);
			success = true;
		} else {
			System.err.println("Delete Error: No LineItem exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem Not Found");
		}

		return success;

	}

}
