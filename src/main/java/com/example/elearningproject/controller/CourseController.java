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

import com.example.elearningproject.model.Course;
import com.example.elearningproject.repository.CourseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	// Getting list of courses
	@GetMapping("/getCourses")
	public ResponseEntity<List<Course>> getCourses(){

		List<Course> courses = new ArrayList<Course>();

		courseRepository.findAll().forEach(courses::add);

		return new ResponseEntity<>(courses,HttpStatus.OK);
	}

	// Adding new Course
	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse(@RequestBody Course course){

		Course _course = courseRepository.save(new Course(course.getCourseName(), course.getCourseDesc(), course.getCourseFee()));

		return new ResponseEntity<>(_course, HttpStatus.CREATED);

	}
	
	//Getting Course By Id
		@GetMapping("/course/{id}")
		public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id){

			Optional<Course> courseData = courseRepository.findById(id);

			if(courseData.isPresent()) {
				return new ResponseEntity<>(courseData.get(),HttpStatus.OK);
			} 
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		//updating  course By Id
		@PutMapping("/course/{id}")
		public ResponseEntity<Course> updatecourse(@PathVariable("id") Integer id,@RequestBody Course course){

			Optional<Course> courseData = courseRepository.findById(id);

			if(courseData.isPresent()) {
				Course crs = courseData.get();
				crs.setCourseName(course.getCourseName());
				crs.setCourseDesc(course.getCourseDesc());
				crs.setCourseFee(course.getCourseFee());
				
				return new ResponseEntity<>(courseRepository.save(crs),HttpStatus.OK);
			} 
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		// Deleting  Course By Id
		@DeleteMapping("/course/{id}")
		public ResponseEntity<Course> deletecourseById(@PathVariable("id") Integer id){

			try {
				courseRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		

}
