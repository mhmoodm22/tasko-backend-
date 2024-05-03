package com.taskmanager.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.ws.io.entity.CategoryEntity;

import com.taskmanager.ws.io.repository.CategoryRepository;
import com.taskmanager.ws.service.CategoryService;
import com.taskmanager.ws.shared.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository catRepository;

	@Override
	public CategoryDto createCategory(String catName) {

		if (catRepository.findByCatName(catName) != null)
			throw new RuntimeException("Record already exists");

		CategoryEntity catEntity = new CategoryEntity();

		catEntity.setCatName(catName);

		CategoryEntity storeCategory = catRepository.save(catEntity);

		CategoryDto returnValue = new CategoryDto();
		BeanUtils.copyProperties(storeCategory, returnValue);

		return returnValue;

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub

		List<CategoryDto> returnValue = new ArrayList<>();

		List<CategoryEntity> categories = (List<CategoryEntity>) catRepository.findAll();

		for (CategoryEntity catEntity : categories) {
			CategoryDto catDto = new CategoryDto(catEntity.getId(), catEntity.getCatName());

			returnValue.add(catDto);
		}

		return returnValue;

	}

	@Override
	public String deleteCategory(Long id) {
		if (catRepository.findById(id) == null)
			throw new RuntimeException("Record Dosen't exists");
		CategoryEntity catEntity = new CategoryEntity();

		catEntity.setId(id);

		catRepository.delete(catEntity);

		return "Category deleted with id :" + id;
	}

}
