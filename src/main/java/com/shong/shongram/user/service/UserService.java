package com.shong.shongram.user.service;

import org.springframework.stereotype.Service;

import com.shong.shongram.common.MD5HashingEncoder;
import com.shong.shongram.user.domian.User;
import com.shong.shongram.user.repository.UserRepository;



@Service
public class UserService {
	
	private final UserRepository userRepository;
	
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(String loginId
			, String password
			, String name
			, String email) {
		
		String encyptPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encyptPassword, name, email);

		return count == 1;
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, encryptPassword);
	}

}