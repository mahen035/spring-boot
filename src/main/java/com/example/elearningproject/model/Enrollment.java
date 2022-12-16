package com.example.elearningproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ENROLLMENT")
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ENROLL_ID")
	private int enrollmentID;
		
	@ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;
	/*
	 * @Column(name = "USER_ID", insertable =false, updatable = false) private int
	 * userId;
	 * 
	 * @Column(name = "COURSE_ID", insertable =false, updatable = false) private int
	 * courseId;
	 */
	
	public Enrollment() {
		
	}

	//public Enrollment( User user, Course course) {
	public Enrollment(User user, Course course) {	
	super();
		
		this.user = user;
		this.course = course;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	/*
	 * public int getUserId() { return userId; }
	 * 
	 * public void setUserId(int userId) { this.userId = userId; }
	 * 
	 * public int getCourseId() { return courseId; }
	 * 
	 * public void setCourseId(int courseId) { this.courseId = courseId; }
	 */
	
	
}
