package com.reetu.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reetu.beans.Company;

public interface AdminService {
	String checkAdminLogin(String aid,String password);
	List<Company> getCompanies(String status);
	String changeStatus(String email,String status);
}
