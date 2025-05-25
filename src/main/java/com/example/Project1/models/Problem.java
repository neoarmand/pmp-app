package com.example.Project1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne @JoinColumn(name = "property_id", nullable = false) 
    private Property property;
    
    @Column (name = "register_date")
    private String registerDate;
    
    @Column (name = "description_problem")
    private String descriptionProblem;
    
    @Column (name = "status")
    private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getDescriptionProblem() {
		return descriptionProblem;
	}

	public void setDescriptionProblem(String descriptionProblem) {
		this.descriptionProblem = descriptionProblem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
