package com.prs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.VendorRepo;
import com.prs.model.Vendor;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorController {
	@Autowired // Initializes create variable
	private VendorRepo vendorRepo;

	@GetMapping("/")
	public List<Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}

	@GetMapping("/{id}")
	public Vendor getVendorById(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
		// TODO Add null check for valid id
		return v.get();
	}

	@PostMapping("")
	public Vendor addVendor(@RequestBody Vendor vendor) {

		// TODO Check for existence by vendor.getId()before save
		return vendorRepo.save(vendor);

	}

	@PutMapping("/{id}")
	public Vendor updateVendor(@PathVariable int id, @RequestBody Vendor vendor) {

		Vendor v = null;

		if (id != vendor.getId()) {
			System.err.println("Vendor id {" + vendor.getId() + "] not match path id[" + id + "].");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendor Not Found");
		} else if (!vendorRepo.existsById(id)) {
			System.err.println("Vendor does not exist");
		} else {
			try {
				v = vendorRepo.save(vendor);
			} catch (Exception e) {
				System.err.println(e);
				throw e;
			}
		}
		return v;
	}

	@DeleteMapping("/{id}")
	public boolean deleteVendor(@PathVariable int id) {
		boolean success = false;
		if (vendorRepo.existsById(id)) {
			vendorRepo.deleteById(id);
			success = true;
		} else {
			System.err.println("Delete Error: No vendor exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor Not Found");
		}
		return success;
	}
}
