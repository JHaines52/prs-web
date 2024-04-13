package com.prs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.model.Request;
import com.prs.model.User;
import com.prs.model.UserLogin;
import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired // Initializes create variable
	private UserRepo userRepo;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent()) {
			return u.get();
		}
		else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "User Not Found:");
		}
		
	}

	@PostMapping("")
	public User addUser(@RequestBody User user) {
		 /*Optional<User> User = userRepo.findByUser(user.getUsername());
		 
		  if (User.isPresent()) {
			  System.err.println("A request with the same description and date needed already exists.");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Not Valid");
		    }*/
		// TODO Check for existence by user.getId()before save
		return userRepo.save(user);

	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		
		User u = null;
		
		if(id != user.getId()) {
			System.err.println("User id {" + user.getId()+"] not match path id["+id+"].");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "User Not Found");
		}
			else if(!userRepo.existsById(id)){
				System.err.println("User does not exist");
			}
			else {
				try {
					u = userRepo.save(user);
				}
				catch (Exception e) {
					System.err.println(e);
					throw e;
				}
			}
			return u;	
	}

	@DeleteMapping("/{id}")
	public boolean deleteUser(@PathVariable int id) {
		boolean success = false;
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			success = true;
		} else {
			System.err.println("Delete Error: No user exists for id: " + id);
			success = false;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}
		return success;
	}

	@PostMapping("/login")

	public User login(@RequestBody UserLogin ul) {
		User user = userRepo.findByUsernameAndPassword(ul.getUsername(), ul.getPassword());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username or password not found");

		}
		return user;
	}

}
