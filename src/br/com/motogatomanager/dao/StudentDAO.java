package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.StudentGroup;

public class StudentDAO {
	private Connection connection;

	public StudentDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(Student student) {
		String sql = "insert into student "
				+ "(name,last_name,birth_date,gender,diagnosis_level,coins,buildings_count,school_id,student_group_id)" 
				+ " values (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, student.getName());
			stmt.setString(2, student.getLast_name());
			java.sql.Date birthDate = new java.sql.Date (student.getBirth_date().getTime());
			stmt.setDate(3, birthDate);
			stmt.setString(4, student.getGender());
			stmt.setString(5, student.getDiagnosis_level());
			stmt.setInt(6, student.getCoins());
			stmt.setInt(7, student.getBuildings_count());
			stmt.setInt(8, student.getSchool().getId());
			stmt.setInt(9, student.getStudent_group().getId());

			stmt.execute();
			
			String lastId = "select SCOPE_IDENTITY()";
			stmt = connection.prepareStatement(lastId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				student.setId(rs.getInt("SCOPE_IDENTITY()"));
			}
			rs.close();
			stmt.close();
			
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Student student) {
		String sql = "update student "
				+ "set name = ?,last_name = ?,birth_date = ?,gender = ?,diagnosis_level = ?,coins = ?,buildings_count = ?,school_id = ?,student_group_id = ? " 
				+ "where student.student_id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, student.getName());
			stmt.setString(2, student.getLast_name());
			java.sql.Date birthDate = new java.sql.Date (student.getBirth_date().getTime());
			stmt.setDate(3, birthDate);
			stmt.setString(4, student.getGender());
			stmt.setString(5, student.getDiagnosis_level());
			stmt.setInt(6, student.getCoins());
			stmt.setInt(7, student.getBuildings_count());
			stmt.setInt(8, student.getSchool().getId());
			stmt.setInt(9, student.getStudent_group().getId());
			stmt.setInt(10, student.getId());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Student> fetchAll () {
		try {
			List<Student> students = new ArrayList<Student>();
			
			String sql = "select * from student";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Student student = new Student ();
				student.setId(rs.getInt("student_id"));
				student.setName(rs.getString("name"));
				student.setLast_name(rs.getString("last_name"));
				student.setGender(rs.getString("gender"));
				java.util.Date birthDate = new java.util.Date (rs.getDate("birth_date").getTime());
				student.setBirth_date(birthDate);
				student.setDiagnosis_level(rs.getString("diagnosis_level"));
				student.setCoins(rs.getInt("coins"));
				student.setBuildings_count(rs.getInt("buildings_count"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				student.setSchool(s);
				
				StudentGroup g = new StudentGroup ();
				s.setId(rs.getInt("student_group_id"));
				student.setStudent_group(g);
				
				students.add (student);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return students;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Student> fetchBySchool (School school) {
		try {
			List<Student> students = new ArrayList<Student>();
			
			String sql = "select s.*, sg.name as sg_name from student s"
					+ " left join student_group sg"
					+ " on s.student_group_id = sg.student_group_id"
					+ " where s.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Student student = new Student ();
				student.setId(rs.getInt("student_id"));
				student.setName(rs.getString("name"));
				student.setLast_name(rs.getString("last_name"));
				student.setGender(rs.getString("gender"));
				java.util.Date birthDate = new java.util.Date (rs.getDate("birth_date").getTime());
				student.setBirth_date(birthDate);
				student.setDiagnosis_level(rs.getString("diagnosis_level"));
				student.setCoins(rs.getInt("coins"));
				student.setBuildings_count(rs.getInt("buildings_count"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				student.setSchool(s);
				
				StudentGroup g = new StudentGroup ();
				g.setId(rs.getInt("student_group_id"));
				g.setName(rs.getString("sg_name"));
				student.setStudent_group(g);
				
				students.add (student);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return students;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
