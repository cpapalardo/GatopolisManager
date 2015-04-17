package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Teacher;
import br.com.motogatomanager.util.EncodingUtil;

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
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, EncodingUtil.ConvertToISO(teacher.getName()));//TODO encoding
			stmt.setString(2, EncodingUtil.ConvertToISO(teacher.getLast_name()));//TODO encoding
			stmt.setString(3, teacher.getPasscode());
			stmt.setString(4, teacher.getEmail());
			stmt.setString(5, EncodingUtil.ConvertToISO(teacher.getQuestion()));//TODO encoding
			stmt.setString(6, EncodingUtil.ConvertToISO(teacher.getAnswer()));//TODO encoding
			stmt.setInt(7, teacher.getSchool().getId());

			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				teacher.setId(rs.getInt(1));
			}
			rs.close();
			
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
			
			stmt.setString(1, EncodingUtil.ConvertToISO(teacher.getName()));//TODO encoding
			stmt.setString(2, EncodingUtil.ConvertToISO(teacher.getLast_name()));//TODO encoding
			stmt.setString(3, teacher.getPasscode());
			stmt.setString(4, teacher.getEmail());
			stmt.setString(5, EncodingUtil.ConvertToISO(teacher.getQuestion()));//TODO encoding
			stmt.setString(6, EncodingUtil.ConvertToISO(teacher.getAnswer()));//TODO encoding
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
				teacher.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				teacher.setLast_name(EncodingUtil.ConvertToUTF8(rs.getString("last_name")));//TODO encoding
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setQuestion(EncodingUtil.ConvertToUTF8(rs.getString("question")));//TODO encoding
				teacher.setAnswer(EncodingUtil.ConvertToUTF8(rs.getString("answer")));//TODO encoding
				
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
				teacher.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				teacher.setLast_name(EncodingUtil.ConvertToUTF8(rs.getString("last_name")));//TODO encoding
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setQuestion(EncodingUtil.ConvertToUTF8(rs.getString("question")));//TODO encoding
				teacher.setAnswer(EncodingUtil.ConvertToUTF8(rs.getString("answer")));//TODO encoding
				
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
	
	public Teacher fetchByNameAndSchool (String name, String lastName, School school) {
		
		try {
			Teacher teacher = new Teacher();
			
			String sql = "select * from teacher where teacher.school_id = ? and teacher.name = ? and teacher.last_name = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			stmt.setString(2, EncodingUtil.ConvertToISO(name));//TODO encoding
			stmt.setString(3, EncodingUtil.ConvertToISO(lastName));//TODO encoding
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//Teacher teacher = new Teacher ();
				teacher.setId(rs.getInt("teacher_id"));
				teacher.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				teacher.setLast_name(EncodingUtil.ConvertToUTF8(rs.getString("last_name")));//TODO encoding
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setQuestion(EncodingUtil.ConvertToUTF8(rs.getString("question")));//TODO encoding
				teacher.setAnswer(EncodingUtil.ConvertToUTF8(rs.getString("answer")));//TODO encoding
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				teacher.setSchool(s);
				//teacher.setPicture(rs.getString("picture"));
				
				//teachers.add (teacher);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return teacher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Teacher fetchByNameAndLastNameAndSchool (String name, String lastName, School school) {
		try {
			String sql = "select * from teacher where teacher.name = ? and teacher.last_name = ? and teacher.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, EncodingUtil.ConvertToISO(name));//TODO encoding
			stmt.setString(2, EncodingUtil.ConvertToISO(lastName));//TODO encoding
			stmt.setInt(3, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			Teacher teacher = new Teacher ();
			while (rs.next()) {
				teacher.setId(rs.getInt("teacher_id"));
				teacher.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				teacher.setLast_name(EncodingUtil.ConvertToUTF8(rs.getString("last_name")));//TODO encoding
				teacher.setPasscode(rs.getString("passcode"));
				teacher.setEmail(rs.getString("email"));
				teacher.setQuestion(EncodingUtil.ConvertToUTF8(rs.getString("question")));//TODO encoding
				teacher.setAnswer(EncodingUtil.ConvertToUTF8(rs.getString("answer")));//TODO encoding
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return teacher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
