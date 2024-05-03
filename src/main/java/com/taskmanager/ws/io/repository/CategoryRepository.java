package com.taskmanager.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.ws.io.entity.CategoryEntity;



@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
	CategoryEntity findByCatName(String catName);
}
