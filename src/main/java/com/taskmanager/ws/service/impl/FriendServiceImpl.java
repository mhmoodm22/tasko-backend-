package com.taskmanager.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.ws.io.entity.FriendEntity;
import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.io.repository.FriendRepository;
import com.taskmanager.ws.io.repository.UserRepository;
import com.taskmanager.ws.service.FriendService;
import com.taskmanager.ws.shared.dto.FriendDto;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	FriendRepository friendRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<FriendDto> findAllfreindList(String id) {
		
		List<FriendDto> returnList = new ArrayList<>();

		List<FriendEntity> friendEntities = friendRepository.findAllFriendByUserIdAndStatus(id, 1);
		
		for(FriendEntity entity:friendEntities) {
			
			UserEntity userEntity = userRepository.findByUserId(entity.getFriendId());
			FriendDto returnVal = new FriendDto();

			returnVal.setId(entity.getId());
			returnVal.setUserId(entity.getUserId());
			returnVal.setFriendId(entity.getFriendId());
			returnVal.setStatus(entity.getStatus());
			returnVal.setFriendName(userEntity.getUserName());
			returnVal.setCurrentLabel(userEntity.getCurrentLabel());
			returnVal.setImg(userEntity.getImg());
			
			returnList.add(returnVal);
		}

		return returnList;
	}

	@Override
	public List<FriendDto> findRequestedfreindList(String id) {
		List<FriendDto> returnList = new ArrayList<>();
		List<FriendEntity> friendEntities = friendRepository.findAllFriendByUserIdAndStatus(id, 0);
		
		for(FriendEntity entity:friendEntities) {
			
			UserEntity userEntity = userRepository.findByUserId(entity.getFriendId());
			FriendDto returnVal = new FriendDto();

			returnVal.setId(entity.getId());
			returnVal.setUserId(entity.getUserId());
			returnVal.setFriendId(entity.getFriendId());
			returnVal.setStatus(entity.getStatus());
			returnVal.setFriendName(userEntity.getUserName());
			returnVal.setCurrentLabel(userEntity.getCurrentLabel());
			returnVal.setImg(userEntity.getImg());
			
			returnList.add(returnVal);
		}

		return returnList;
	}
	
	
	@Override
	public List<FriendDto> findfreindList(String id) {
		List<FriendDto> returnList = new ArrayList<>();
		List<FriendEntity> friendEntities = friendRepository.findAllFriendByUserId(id);
		
		
		for(FriendEntity entity:friendEntities) {
			
			UserEntity userEntity = userRepository.findByUserId(entity.getFriendId());
			FriendDto returnVal = new FriendDto();

			returnVal.setId(entity.getId());
			returnVal.setUserId(entity.getUserId());
			returnVal.setFriendId(entity.getFriendId());
			returnVal.setStatus(entity.getStatus());
			returnVal.setFriendName(userEntity.getUserName());
			returnVal.setCurrentLabel(userEntity.getCurrentLabel());
			returnVal.setImg(userEntity.getImg());
			
			returnList.add(returnVal);
		}

		return returnList;
	}


	@Override
	public FriendDto sendFriendRequest(String userId, String friendId) {

		FriendDto returnVal = new FriendDto();

		FriendEntity saveEntity = new FriendEntity();
		saveEntity.setUserId(userId);
		saveEntity.setFriendId(friendId);
		saveEntity.setStatus(0);

		FriendEntity savedEntity = friendRepository.save(saveEntity);

		UserEntity userEntity = userRepository.findByUserId(friendId);

		returnVal.setId(savedEntity.getId());
		returnVal.setUserId(userId);
		returnVal.setFriendId(friendId);
		returnVal.setFriendName(userEntity.getUserName());
		returnVal.setCurrentLabel(userEntity.getCurrentLabel());
		returnVal.setStatus(savedEntity.getStatus());
		returnVal.setImg(userEntity.getImg());

		return returnVal;
	}

	@Override
	public FriendDto acceptFriendRequest(Long id) {

		FriendDto returnVal = new FriendDto();

		FriendEntity savedEntity2 = new FriendEntity();// for creating vice versa friend

		Optional<FriendEntity> findEntity = friendRepository.findById(id);

		FriendEntity friendEntity = findEntity.get();

		friendEntity.setStatus(1);

		FriendEntity savedEntity = friendRepository.save(friendEntity);

		// creating vice versa friend
		savedEntity2.setUserId(savedEntity.getFriendId());
		savedEntity2.setFriendId(savedEntity.getFriendId());
		savedEntity2.setStatus(1);

		friendRepository.save(savedEntity2);

		UserEntity userEntity = userRepository.findByUserId(savedEntity.getFriendId());

		returnVal.setId(savedEntity.getId());
		returnVal.setUserId(savedEntity.getUserId());
		returnVal.setFriendId(savedEntity.getFriendId());
		returnVal.setStatus(savedEntity.getStatus());
		returnVal.setFriendName(userEntity.getUserName());
		returnVal.setCurrentLabel(userEntity.getCurrentLabel());
		returnVal.setImg(userEntity.getImg());

		return returnVal;
	}

	@Override
	public List<FriendDto> findNewFriendRequestedList(String id) {
		List<FriendDto> returnList = new ArrayList<>();

		List<FriendEntity> friendEntities = friendRepository.findAllFriendByFriendIdAndStatus(id, 0);
		
		for(FriendEntity entity:friendEntities) {
			
			UserEntity userEntity = userRepository.findByUserId(entity.getFriendId());
			FriendDto returnVal = new FriendDto();

			returnVal.setId(entity.getId());
			returnVal.setUserId(entity.getUserId());
			returnVal.setFriendId(entity.getFriendId());
			returnVal.setStatus(entity.getStatus());
			returnVal.setFriendName(userEntity.getUserName());
			returnVal.setCurrentLabel(userEntity.getCurrentLabel());
			returnVal.setImg(userEntity.getImg());
			
			returnList.add(returnVal);
		}

		return returnList;
	}

	@Override
	public FriendDto rejectFriendRequest(Long id) {
		FriendDto returnVal = new FriendDto();

		FriendEntity savedEntity2 = new FriendEntity();// for creating vice versa friend

		Optional<FriendEntity> findEntity = friendRepository.findById(id);

		FriendEntity friendEntity = findEntity.get();

		friendEntity.setStatus(1);

		FriendEntity savedEntity = friendRepository.save(friendEntity);

		// creating vice versa friend
		savedEntity2.setUserId(savedEntity.getFriendId());
		savedEntity2.setFriendId(savedEntity.getFriendId());
		savedEntity2.setStatus(2);

		friendRepository.save(savedEntity2);

		UserEntity userEntity = userRepository.findByUserId(savedEntity.getFriendId());

		returnVal.setId(savedEntity.getId());
		returnVal.setUserId(savedEntity.getUserId());
		returnVal.setFriendId(savedEntity.getFriendId());
		returnVal.setStatus(savedEntity.getStatus());
		returnVal.setFriendName(userEntity.getUserName());
		returnVal.setCurrentLabel(userEntity.getCurrentLabel());
		returnVal.setImg(userEntity.getImg());

		return returnVal;
	}


}
