package com.reetu.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.User;

@Repository
public class UserRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean ConfirmPurchase(int pid,String c_email,String email,String v_no,String e_no){
		class DataMapper implements RowMapper{
			public String mapRow(ResultSet rs,int rowNum) throws SQLException{
				return 	rs.getString("v_no");
			}
		}
		try {
			final String query ="select * from policy_orders where v_no=?";
			String r=(String) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {v_no});
			return false;
		}catch(EmptyResultDataAccessException ex) {
			try {
				String query="insert into policy_orders(pid,c_email,u_email,v_no,e_no,c_date) values(?,?,?,?,?,CURRENT_DATE)";
				jdbcTemplate.update(query,new Object[] {pid,c_email,email,v_no,e_no});
				return true;
			}catch(Exception e) {
				ex.printStackTrace();
				return false;
			}
		}
		
	}
	

	public List<HashMap> getPoliciesByUser(String u_email){
		class DataMapper implements RowMapper{
			public HashMap mapRow(ResultSet rs,int rowNum)throws SQLException{
				HashMap policy_order=new HashMap();
				policy_order.put("oid", rs.getInt("oid"));
				policy_order.put("pid", rs.getInt("pid"));
				policy_order.put("v_no", rs.getString("v_no"));
				policy_order.put("e_no", rs.getString("e_no"));
				policy_order.put("c_date", rs.getDate("c_date"));
				policy_order.put("c_email", rs.getString("c_email"));
				return 	policy_order;
			}
		}
		try {
			final String query ="select * from policy_orders where u_email=?";
			List<HashMap> p= jdbcTemplate.query(query,new DataMapper(),new Object[] {u_email});
			return p;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public String addUser(User u){
		try {
			String query="insert into users(email,name,phone,password) values(?,?,?,?)";
			jdbcTemplate.update(query,new Object[] {u.getEmail(),u.getName(),u.getPhone(),u.getPassword()});
			
			return "success";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "already";
		}
	}
	
	public String checkUserLogin(String email,String password){
		class DataMapper implements RowMapper{
			public String mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getString("name");
			}
		}
		try {
			final String query ="select * from users where email=? and password=?";
			String r=(String) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {email,password});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public String userPhotoUpload(MultipartFile image,String email){
		try {
			String query="update users set photo=? where email=?";
			jdbcTemplate.update(query,new Object[] {image.getInputStream(),email});
			
			return "success";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "failed";
		}
	}
	
	public byte[] getUserImage(String email){
		class DataMapper implements RowMapper{
			public byte[] mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getBytes("photo");
			}
		}
		try {
			final String query ="select photo from users where email=?";
			byte[] r=(byte[]) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {email});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
}
