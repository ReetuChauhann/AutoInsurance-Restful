package com.reetu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reetu.beans.Company;
import com.reetu.dao.AdminRepo;
import com.reetu.service.AdminService;

@RestController
public class AdminController {
	                           @Autowired
	                           AdminService As;
	
	
	
	
	@GetMapping(value = "/adminLogin/{aid}/{password}")
	public ResponseEntity<String> checkAdminLogin(@PathVariable String aid, @PathVariable String password) {
		String r=As.checkAdminLogin(aid, password);
		if(r!=null)
			return new ResponseEntity<String>(r, HttpStatus.OK);
		else
			return new ResponseEntity<String>("NotFound", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getCompanies/{status}")
	public List<Company> getCompanies(@PathVariable String status){
		List<Company> companies=As.getCompanies(status);
		return companies;
	}
	
	
	@PutMapping(value = "/changeStatus/{email}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable String email,@PathVariable String status){
		String r=As.changeStatus(email,status);
		return new ResponseEntity<String>(r, HttpStatus.OK);
	}
}
