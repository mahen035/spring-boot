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

import com.example.elearningproject.model.Enrollment;
import com.example.elearningproject.model.User;
import com.example.elearningproject.repository.EnrollmentRepository;
import com.example.elearningproject.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EnrollmentController {

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@GetMapping("/getEnrollment")
	public ResponseEntity<List<Enrollment>> getEnrollment(){

		List<Enrollment> enrollments = new ArrayList<Enrollment>();

		enrollmentRepository.findAll().forEach(enrollments::add);
		
		return new ResponseEntity<>(enrollments,HttpStatus.OK);
	}

	// Adding new Enrollment
	@PostMapping("/addEnrollment")
	public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment){
		
		Enrollment _enrollment = enrollmentRepository.save(enrollment);

		return new ResponseEntity<>(_enrollment, HttpStatus.CREATED);

	}

	//Getting User By Id
	@GetMapping("/enrollment/{id}")
	public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("id") Integer id){

		Optional<Enrollment> enrollmentData = enrollmentRepository.findById(id);

		if(enrollmentData.isPresent()) {
			return new ResponseEntity<>(enrollmentData.get(),HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//updating  User By Id
	@PutMapping("/enrollment/{id}")
	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("id") Integer id,@RequestBody Enrollment enrollment){

		Optional<Enrollment> enrollmentData = enrollmentRepository.findById(id);

		if(enrollmentData.isPresent()) {
			Enrollment usr = enrollmentData.get();
//			usr.setUserName(enrollment.getUserName());
//			usr.setEmail(enrollment.getEmail());
//			usr.setAddress(enrollment.getAddress());
			
			return new ResponseEntity<>(enrollmentRepository.save(usr),HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Deleting Enrollment By Id
	@DeleteMapping("/enrollment/{id}")
	public ResponseEntity<User> deleteEnrollmentById(@PathVariable("id") Integer id){

		try {
			enrollmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
