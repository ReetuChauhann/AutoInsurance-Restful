package com.reetu.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Company;
import com.reetu.beans.Policy;

public interface CompanyService {
	String companyRegister(Company c);
	String checkCompanyLogin(String email,String password);
	String getCompanyStatus(String email);
	String addPolicy(Policy p);
	List<Policy> getPolicies(String email);
	List<Policy> getPoliciesByCategory(String category);
	String companyLogoUpload(MultipartFile image,String email);
	byte [] getImage(String email);
	
}
