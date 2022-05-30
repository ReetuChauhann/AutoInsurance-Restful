package com.reetu.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.User;
import com.reetu.dao.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public String userRegister(User u) {
		// TODO Auto-generated method stub
		return userRepo.addUser(u);
	}

	@Override
	public String checkUserLogin(String email, String password) {
		// TODO Auto-generated method stub
		return userRepo.checkUserLogin(email, password);
	}

	@Override
	public String userPhotoUpload(MultipartFile image, String email) {
		// TODO Auto-generated method stub
		return userRepo.userPhotoUpload(image, email);
	}

	@Override
	public byte[] getUserImage(String email) {
		// TODO Auto-generated method stub
		return userRepo.getUserImage(email);
	}

	@Override
	public boolean ConfirmPurchase(int pid, String c_email, String email, String v_no, String e_no) {
		
		// TODO Auto-generated method stub
		return userRepo.ConfirmPurchase(pid, c_email, email, v_no, e_no);
	}

	@Override
	public List<HashMap> getPoliciesByUser(String u_email) {
		// TODO Auto-generated method stub
		return userRepo.getPoliciesByUser(u_email);
	}
	
	
}

