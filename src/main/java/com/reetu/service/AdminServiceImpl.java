package com.reetu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reetu.beans.Company;
import com.reetu.dao.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {
	
	                @Autowired
	                AdminRepo adminRepo;

					@Override
					public String checkAdminLogin(String aid, String password) {
						// TODO Auto-generated method stub
						return adminRepo.checkAdminLogin(aid, password);
					}

					@Override
					public List<Company> getCompanies(String status) {
						// TODO Auto-generated method stub
						return adminRepo.getCompanies(status);
					}

					@Override
					public String changeStatus(String email, String status) {
						// TODO Auto-generated method stub
						return adminRepo.changeStatus(email, status);
					}
	
}
