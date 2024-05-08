package com.taskmanager.ws.service;

import java.util.List;

import com.taskmanager.ws.shared.dto.CategoryDto;


public interface CategoryService {
	
	CategoryDto createCategory(String name);
	
	List<CategoryDto> getAllCategory();
	
	String deleteCategory(Long id);

}
