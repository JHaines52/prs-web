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
	private LineItemRepo lineItemRepo;

	@Autowired
	private RequestRepo requestRepo;

	@Transactional
	private void recalculateRequestTotal(Request request) {
		// Fetch all LineItems associated with the given Request
		List<LineItem> lineItems = lineItemRepo.findByRequestId(request.getId());

		// Calculate the total
		double total = lineItems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();

		// Set the new total and save the Request
		request.setTotal((float) total);
		requestRepo.save(request);
	}
// lines for requests
	@GetMapping("/lines/{requestId}")
	public List<LineItem> getLinesForRequest(@PathVariable int requestId) {
		List<LineItem> lineItems = lineItemRepo.findByRequestId(requestId);

		if (lineItems.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lineitem Not Found"); // Return 404 Not Found if
																							// there are no line items
		}

		return lineItems; // Return 200 OK with line items in the body
	}
	
	@GetMapping("/")
	public List<LineItem> getAllLineItems() {
		return lineItemRepo.findAll();
	}

	@GetMapping("/{id}")
	public LineItem getLineItemById(@PathVariable int id) {
		Optional<LineItem> item = lineItemRepo.findById(id);
		if (item.isPresent()) {
			return item.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lineitem Not Found");
		}
	}

	// Method to add a new Lineitem
	@PostMapping("")
	public LineItem addLineItem(@RequestBody LineItem lineItem) {

		Request request = lineItem.getRequest();
		lineItemRepo.save(lineItem);
		recalculateRequestTotal(request);
		return lineItem;

	}

	@PostMapping("/calculatetotal/{id}")
	public float updateTotal(@PathVariable int id) {
		LineItem lineItem = lineItemRepo.findById(id).get();
		Request request = lineItem.getRequest();
		recalculateRequestTotal(request);
		float newTotal = request.getTotal();
		return newTotal;
	}

	@PutMapping("/{id}")
	public LineItem updateLineItem(@PathVariable int id, @RequestBody LineItem lineItem) {

		LineItem item = null;
		Request request = lineItem.getRequest();

		if (id != lineItem.getId()) {
			System.err.println("Lineitem id does not match path id.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lineitem Not Found");

		} else if (!lineItemRepo.existsById(id)) {
			System.err.println("Lineitem does not exist for id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
		} else {
			try {
				item = lineItemRepo.save(lineItem);
				recalculateRequestTotal(request);
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		}
		
		return item;
	}

	@DeleteMapping("/{id}")
	public boolean deleteLineItem(@PathVariable int id) {
		boolean success = false;

		if (lineItemRepo.existsById(id)) {
			LineItem lineItem = lineItemRepo.findById(id).get();
			Request request = lineItem.getRequest();
			lineItemRepo.delete(lineItem);
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
