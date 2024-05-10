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

import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.io.repository.UserRepository;
import com.taskmanager.ws.service.UserService;
import com.taskmanager.ws.shared.Utils;
import com.taskmanager.ws.shared.dto.UserDto;

@Service
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
		userEntity.setEarnedPoint(00);//total earned point
		userEntity.setCurrentLabel("Label 0");//current level
		userEntity.setCurrentPoint(00);//show the point earened on current level
		userEntity.setNextLabelPoint(100);//point required to earn next level
		userEntity.setLabelIndex(0);//current label


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
	public UserDto getUserByUserId(String id) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		UserEntity returnEntity = userRepository.save(userEntity);

		BeanUtils.copyProperties(returnEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto uploadImage(String id, byte[] image) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		try {
			java.sql.Blob img = new SerialBlob(image);
			userEntity.setImg(img);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public List<UserDto> getAllUser(String id) {

		List<UserDto> returnValue = new ArrayList<>();
		UserEntity userEntity = userRepository.findByUserId(id);

		List<UserEntity> allUser = (List<UserEntity>) userRepository.findAll();

		allUser.remove(userEntity);

		for (UserEntity user : allUser) {

			UserDto userDto = new UserDto();

			// try {
			//
			// StringBuffer fileData = new StringBuffer();
			// BufferedReader reader = new BufferedReader(new FileReader(
			// "C:\\Users\\bdCalling\\Pictures\\Screenshots\\Screenshot 2024-03-15
			// 103816.png"));
			// char[] buf = new char[1024];
			// int numRead = 0;
			// while ((numRead = reader.read(buf)) != -1) {
			// String readData = String.valueOf(buf, 0, numRead);
			// fileData.append(readData);
			// buf = new char[1024];
			// }
			// reader.close();
			//
			// System.out.println(fileData.toString());
			//
			// } catch (Exception e) {
			// // TODO: handle exception
			// }
			if (user.getImg() != null) {
				try {
					InputStream inputStream = user.getImg().getBinaryStream();

					// Read the data into a byte array
					byte[] data = new byte[(int) user.getImg().length()];
					inputStream.read(data);

					// Convert the byte array to a string
					// String blobData = new String(data);

					IOUtils.readFully(inputStream, data, 0, 0);

					// Print the Blob data to the console

					java.sql.Blob blob = new SerialBlob(data);
					System.out.println(data);

					// byte[] data2 = blob.getBinaryStream().read();
					// System.out.println(data2);
					// System.out.println(data.length);

					java.sql.Blob blob2 = new SerialBlob(data);
					userDto.setImg(blob2);

					// Close the input stream
					inputStream.close();
					System.out.println(inputStream.toString());
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			userDto.setUserId(user.getUserId());

			userDto.setUserName(user.getUserName());
			userDto.setCurrentLabel(user.getCurrentLabel());

			returnValue.add(userDto);
		}

		return returnValue;
	}

}
