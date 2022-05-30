package com.reetu.service;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.User;

public interface UserService {
	String userRegister(User u);
	String checkUserLogin(String email,String password);
	String userPhotoUpload(MultipartFile image,String email);
	byte[] getUserImage(String email);

	boolean ConfirmPurchase(int pid,String c_email,String email,String v_no,String e_no);
	List<HashMap> getPoliciesByUser(String u_email);
}

