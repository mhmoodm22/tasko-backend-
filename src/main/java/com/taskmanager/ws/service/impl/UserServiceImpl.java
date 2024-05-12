package com.taskmanager.ws.service.impl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.io.repository.UserRepository;
import com.taskmanager.ws.service.UserService;
import com.taskmanager.ws.shared.Utils;
import com.taskmanager.ws.shared.dto.UserDto;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Record already exists");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEarnedPoint(00);// total earned point
		userEntity.setCurrentLabel("Level 0");// current level
		userEntity.setCurrentPoint(00);// show the point earened on current level
		userEntity.setNextLabelPoint(100);// point required to earn next level
		userEntity.setLabelIndex(0);// current label

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity users = userRepository.findByEmail(email);
		;
		if (users == null)
			throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(users, returnValue);
		return returnValue;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserByUserId(String id) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto uploadImage(String id, byte[] image) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		userEntity.setImg(image);

		UserEntity returnEntity = userRepository.save(userEntity);
		BeanUtils.copyProperties(returnEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updatePassword(String email, String password) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		userEntity.setPassword(password);
		UserEntity returnEntity = userRepository.save(userEntity);

		BeanUtils.copyProperties(returnEntity, returnValue);
		return returnValue;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUser(String id) {

		List<UserDto> returnValue = new ArrayList<>();
		UserEntity userEntity = userRepository.findByUserId(id);

		List<UserEntity> allUser = (List<UserEntity>) userRepository.findAll();

		allUser.remove(userEntity);

		for (UserEntity user : allUser) {
			user.getImg();
	

			UserDto userDto = new UserDto();

			userDto.setUserId(user.getUserId());
			userDto.setImg(user.getImg());
			userDto.setUserName(user.getUserName());
			userDto.setCurrentLabel(user.getCurrentLabel());

			returnValue.add(userDto);
		}

		return returnValue;
	}

}
