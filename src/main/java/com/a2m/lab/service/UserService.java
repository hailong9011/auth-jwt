package com.a2m.lab.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a2m.lab.constants.RoleConstants;
import com.a2m.lab.entities.UserEntity;
import com.a2m.lab.entities.UserRoleEntity;
import com.a2m.lab.repository.UserRepository;
import com.a2m.lab.repository.UserRoleRepository;

@Service
public class UserService implements UserDetailsService {
	Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserRoleRepository userRoleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserEntity saveUser(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		UserEntity userSaved = userRepo.save(userEntity);
		addRoleToUser(userSaved);
		return userSaved;
	}

	public void addRoleToUser(UserEntity userEntity) {
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setUserId(userEntity.getId());
		userRole.setRoleId(RoleConstants.ROLE_ADMIN);
		userRoleRepo.save(userRole);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByUsername(username);
		if (userEntity == null) {
			logger.error("User not found !");
			throw new UsernameNotFoundException("Username not found !");
		}

		List<UserRoleEntity> listUserRole = userRoleRepo.findByUserId(userEntity.getId());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		listUserRole.forEach(userRole -> {
			authorities.add(new SimpleGrantedAuthority(userRole.getRoleId()));
		});
		return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
	}
}
