package com.taskmanager.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.taskmanager.ws.io.entity.LevelEntity;





@Repository
public interface LavelRepository extends CrudRepository<LevelEntity, Long> {
	LevelEntity findFirstByLabellimitGreaterThan(int limit);
	LevelEntity findFirstByLabellimitLessThanEqual(int limit);
	
}
