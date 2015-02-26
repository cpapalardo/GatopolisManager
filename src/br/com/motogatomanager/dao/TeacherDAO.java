package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Teacher;

public class TeacherDAO {
	private Connection connection;

	public TeacherDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(Teacher teacher) {
		String sql = "insert into teacher "
				+ "(name,last_name,passcode,email,question,answer,school_id)" 
				+ " values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, teacher.getName());
			stmt.setString(2, teacher.getLast_name());
			stmt.setString(3, teacher.getPasscode());
			stmt.setString(4, teacher.getEmail());
			stmt.setString(5, teacher.getQuestion());
			stmt.setString(6, teacher.getAnswer());
			stmt.setInt(7, teacher.getSchool().getId());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Teacher teacher) {
		String sql = "update teacher "
				+ "set name = ?,last_name = ?,passcode = ?,email = ?,question = ?,answer = ?,school_id = ? "
				+ "where teacher.teacher_id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, teacher.getName());
			stmt.setString(2, teacher.getLast_name());
			stmt.setString(3, teacher.getPasscode());
			stmt.setString(4, teacher.getEmail());
			stmt.setString(5, teacher.getQuestion());
			stmt.setString(6, teacher.getAnswer());
			stmt.setInt(7, teacher.getSchool().getId());
			stmt.setInt(8, teacher.getId());
			
			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Teacher> fetchAll () {
		try {
			List<Teacher> teachers = new ArrayList<Teacher>();
			
			String sql = "select * from teacher";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher ();
				teacher.setId(rs.getInt("teacher_id"));
				teacher.setName(rs.getString("name"));
				teacher.setLast_name(rs.getString("last_name"));
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setQuestion(rs.getString("question"));
				teacher.setAnswer(rs.getString("answer"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				teacher.setSchool(s);
				
				teachers.add (teacher);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return teachers;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Teacher> fetchBySchool (School school) {
		try {
			List<Teacher> teachers = new ArrayList<Teacher>();
			
			String sql = "select * from teacher where teacher.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher ();
				teacher.setId(rs.getInt("teacher_id"));
				teacher.setName(rs.getString("name"));
				teacher.setLast_name(rs.getString("last_name"));
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setIs_coordinator(rs.getBoolean("is_coordinator"));
				teacher.setQuestion(rs.getString("question"));
				teacher.setAnswer(rs.getString("answer"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				teacher.setSchool(s);
				//teacher.setPicture(rs.getString("picture"));
				
				teachers.add (teacher);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return teachers;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
