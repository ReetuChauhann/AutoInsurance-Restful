package com.reetu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Company;
import com.reetu.beans.Policy;
import com.reetu.dao.CompanyRepo;
import com.reetu.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService cs;
	
	
	@PostMapping(value = "/companyRegister") 
	public String companyRegister(@RequestBody Company c) {
		String s=cs.companyRegister(c);
		return s;
		
		
	}
	
	
	@PostMapping(value = "/addPolicy") 
	public ResponseEntity<String> addPolicy(@RequestBody Policy p) {
		String r=cs.addPolicy(p);
		if(r.equalsIgnoreCase("success")) {
			return new ResponseEntity<String>(p.getPname()+" is Added Successfully!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(p.getPname()+" insertion failed!", HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value = "/companyLogin/{email}/{password}")
	public ResponseEntity<String[]> checkCompanyLogin(@PathVariable String email, @PathVariable String password) {
		String r=cs.checkCompanyLogin(email, password);
		if(r!=null)
			return new ResponseEntity<String[]>(new String[] {email,r}, HttpStatus.OK);
		else
			return new ResponseEntity<String[]>(new String[] {}, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getCompanyStatus/{email}")
	public ResponseEntity<String> getCompanyStatus(@PathVariable String email){
		String r=cs.getCompanyStatus(email);
		if(r!=null)
			return new ResponseEntity<String>(r, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Not Found", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getPolicies/{email}")
	public List<Policy> getPolicies(@PathVariable String email){
		List<Policy> policies=cs.getPolicies(email);
		return policies;
	}
	
	
	@GetMapping(value = "/getPoliciesByCategory/{category}")
	public List<Policy> getPoliciesByCategory(@PathVariable String category){
		List<Policy> policies=cs.getPoliciesByCategory(category);
		return policies;
	}

	
	@PostMapping(value = "/companyLogoUpload/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> companyLogoUpload(@RequestPart("logo") MultipartFile image,@PathVariable String email) {
		String r=cs.companyLogoUpload(image, email);
		return new ResponseEntity<String>(r, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getImage/{email}")
	public ResponseEntity<byte[]> getImage(@PathVariable String email){
		byte[] b=cs.getImage(email);
		if(b!=null) {
			return new ResponseEntity<byte[]>(b, HttpStatus.OK);
		}else {
			return null;
		}
	}
}

