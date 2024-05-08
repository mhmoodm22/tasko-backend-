package com.taskmanager.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.ws.service.CategoryService;
import com.taskmanager.ws.shared.dto.CategoryDto;
import com.taskmanager.ws.shared.dto.UserDto;
import com.taskmanager.ws.ui.model.request.UserDetailsRequestModel;
import com.taskmanager.ws.ui.model.response.CategoryRest;
import com.taskmanager.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping
	public CategoryRest createCategory(@RequestParam String category) {

		CategoryRest returnValue = new CategoryRest();

		CategoryDto createCategory = categoryService.createCategory(category);
		BeanUtils.copyProperties(createCategory, returnValue);

		return returnValue;
	}

	@DeleteMapping
	public String deleteCategory(@RequestParam Long id) {

		String returnValue = categoryService.deleteCategory(id);

		return returnValue;
	}

	@GetMapping
	public List<CategoryRest> allCategory() {



		List<CategoryRest> returnValue = new ArrayList<>();

		List<CategoryDto> categoryList = categoryService.getAllCategory();

		for (CategoryDto categoryDto : categoryList) {

			CategoryRest catRest = new CategoryRest(categoryDto.getId(),categoryDto.getCatName());
			
			returnValue.add(catRest);

		}

		return returnValue;

	}

}
