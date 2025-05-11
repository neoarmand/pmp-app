package com.example.Project1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")

public class Property {
	    
	    @Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    @Column (name = "property_name")
	    private String propertyName;
	    
	    @Column (name = "status")
	    private String status;
	    
	    @Column (name = "acquisition_date")
	    private String acquisitionDate;
	    

	    public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAcquisitionDate() {
			return acquisitionDate;
		}

		public void setAcquisitionDate(String acquisitionDate) {
			this.acquisitionDate = acquisitionDate;
		}

		public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

}