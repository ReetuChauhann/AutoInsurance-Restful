package com.reetu.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.User;
import com.reetu.dao.UserRepo;
import com.reetu.service.UserService;

@RestController
public class UserContorller {
	
	@Autowired
	UserService us;
	
	
	@PostMapping(value = "/userRegister") 
	public ResponseEntity<String> userRegister(@RequestBody User u) {
		String r=us.userRegister(u);
		if(r.equalsIgnoreCase("success")) {
			return new ResponseEntity<String>(u.getName()+" is Registered Successfully!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(u.getName()+" is Already Exist!", HttpStatus.OK);
		}
	}
	


	@RequestMapping(value = "/ConfirmPurchase/{pid}/{c_email}/{email}/{v_no}/{e_no}") 
	public String ConfirmPurchase(@PathVariable int pid, @PathVariable String c_email, @PathVariable String email,@PathVariable  String v_no, @PathVariable String e_no) {
		boolean b=us.ConfirmPurchase(pid, c_email, email, v_no, e_no);
		if(b) {
			return "Insurance Purchased successfully.";
		}else {
			return "Insurance already Purchased for this Vehicle!";
		}
	}
	
	
	@GetMapping(value = "/getPoliciesByUser/{u_email}") 
	public List<HashMap> getPoliciesByUser(@PathVariable String u_email) {
		List<HashMap> b=us.getPoliciesByUser(u_email);
		return b;
	}

	

	@GetMapping(value = "/userLogin/{email}/{password}")
	public ResponseEntity<String[]> checkUserLogin(@PathVariable String email, @PathVariable String password) {
		String r=us.checkUserLogin(email, password);
		if(r!=null)
			return new ResponseEntity<String[]>(new String[] {email,r}, HttpStatus.OK);
		else
			return new ResponseEntity<String[]>(new String[] {}, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/userPhotoUpload/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> userPhotoUpload(@RequestPart("photo") MultipartFile image,@PathVariable String email) {
		String r=us.userPhotoUpload(image, email);
		return new ResponseEntity<String>(r, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getUserImage/{email}")
	public ResponseEntity<byte[]> getUserImage(@PathVariable String email){
		byte[] b=us.getUserImage(email);
		if(b!=null) {
			return new ResponseEntity<byte[]>(b, HttpStatus.OK);
		}else {
			return null;
		}
	}
}

