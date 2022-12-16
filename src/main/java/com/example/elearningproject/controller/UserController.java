package com.example.elearningproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.elearningproject.model.User;
import com.example.elearningproject.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){

		List<User> users = new ArrayList<User>();

		userRepository.findAll().forEach(users::add);
		System.out.println(users);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

	// Adding new User
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user){
		// do for all non-mandotory fields
		String address ="";
		if(user.getAddress() != null || user.getAddress() != "") {
			address = user.getAddress();
		}

		User _user = userRepository.save(new User(user.getUserName(),user.getEmail(),user.getAddress(),user.getUserPhone(),user.getPassword()));

		return new ResponseEntity<>(_user, HttpStatus.CREATED);

	}

	//Getting User By Id
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){

		Optional<User> userData = userRepository.findById(id);

		if(userData.isPresent()) {
			return new ResponseEntity<>(userData.get(),HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//updating  User By Id
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateuser(@PathVariable("id") Integer id,@RequestBody User user){

		Optional<User> userData = userRepository.findById(id);

		if(userData.isPresent()) {
			User usr = userData.get();
			usr.setUserName(user.getUserName());
			usr.setEmail(user.getEmail());
			usr.setAddress(user.getAddress());
			usr.setPassword(user.getPassword());
			usr.setUserPhone(user.getUserPhone());
			return new ResponseEntity<>(userRepository.save(usr),HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Deleting  User By Id
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") Integer id){

		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/loginUser")
	public ResponseEntity<User> loginUser(@RequestBody User user){

		System.out.println("user"+user);
		ResponseEntity<User> rs = new ResponseEntity<>(HttpStatus.CREATED);
		List<User> userData = userRepository.findAll();

		for(User usr:userData) {
			
			if (usr.getUserName().equalsIgnoreCase(user.getUserName()) && 
					usr.getEmail().equalsIgnoreCase(user.getEmail()) && 
					usr.getPassword().equals(user.getPassword()))
			{
				rs = new ResponseEntity<>(usr, HttpStatus.OK);
			}
			else
			{
				rs.status(HttpStatus.NOT_FOUND);
			}
		}
		
		return rs;

	}
}


