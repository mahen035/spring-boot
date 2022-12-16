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
@Table(name="FEEDBACK")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FEEDBACK_ID")
	private int feedbackId;
	
	@Column(name="FEEDBACK")
	private String feedback;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

	public Feedback() {
		
	}

	public Feedback(int feedbackId, String feedback, User user) {
		super();
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.user = user;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
