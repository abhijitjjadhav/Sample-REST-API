package com.telusko.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {
	
	List<Alien> aliens;
	
	Connection con = null;
	String URL = "jdbc:mysql://localhost:3306/";
	String DATABASE = "restdb";
	String USERNAME = "alien";
	String PASSWORD = "Test@1234";
	
	public AlienRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		
		String sql = "SELECT * FROM alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return aliens;
	}
	
	public Alien getAlien(int id) {
		
		Alien a;
		String sql = "SELECT * FROM alien WHERE id = " + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				return a;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	public void create(Alien a1) {
		String sql = "INSERT INTO alien VALUES(?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void update(Alien a1) {
		String sql = "UPDATE alien set name=?, points=? WHERE id=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId()); 
			
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void delete(Alien a1) {
		String sql = "DELETE FROM alien WHERE id=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId()); 
			
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
}
