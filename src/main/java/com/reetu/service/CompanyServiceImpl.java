package com.reetu.service;

import java.util.List;

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

import com.reetu.beans.Company;
import com.reetu.beans.Policy;
import com.reetu.dao.CompanyRepo;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyRepo companyRepo;

	@Override
	public String companyRegister(Company c) {
		// TODO Auto-generated method stub
		return companyRepo.addCompany(c);
	}

	@Override
	public String checkCompanyLogin(String email, String password) {
		// TODO Auto-generated method stub
		return companyRepo.checkCompanyLogin(email, password);
	}

	@Override
	public String getCompanyStatus(String email) {
		// TODO Auto-generated method stub
		return companyRepo.getCompanyStatus(email);
	}

	@Override
	public String addPolicy(Policy p) {
		// TODO Auto-generated method stub
		return companyRepo.addPolicy(p);
	}

	@Override
	public List<Policy> getPolicies(String email) {
		// TODO Auto-generated method stub
		return companyRepo.getPolicies(email);
	}

	@Override
	public List<Policy> getPoliciesByCategory(String category) {
		// TODO Auto-generated method stub
		return companyRepo.getPolicies(category);
	}

	@Override
	public String companyLogoUpload(MultipartFile image, String email) {
		// TODO Auto-generated method stub
		return companyRepo.companyLogoUpload(image, email);
	}

	@Override
	public byte[] getImage(String email) {
		// TODO Auto-generated method stub
		return companyRepo.getImage(email);
	}
	
}

