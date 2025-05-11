package com.example.Project1.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Project1.models.Property;

public interface PropertyService {

    List<Property> getAllProperties();

    void saveProperty(Property property);
    
    Property getpropertyById(long id);
    
    void deletePropertyById(long id);
    
    Page<Property> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
