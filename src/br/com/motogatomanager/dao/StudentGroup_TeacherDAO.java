package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.StudentGroup_Teacher;
import br.com.motogatomanager.modelo.Teacher;

public class StudentGroup_TeacherDAO {
	private Connection connection;

	public StudentGroup_TeacherDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(StudentGroup_Teacher sg_t) {
		String sql = "insert into student_group_teacher "
				+ "(school,student_group_id,teacher_id)" 
				+ " values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, sg_t.getSchool().getId());
			stmt.setInt(2, sg_t.getStudent_group_id().getId());
			stmt.setInt(3, sg_t.getTeacher_id().getId());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<StudentGroup_Teacher> fetchAll () {
		try {
			List<StudentGroup_Teacher> sg_tList = new ArrayList<StudentGroup_Teacher>();
			
			String sql = "select * from student_group_teacher";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup_Teacher sg_t = new StudentGroup_Teacher ();
				sg_t.setId(rs.getInt("student_group_teacher_id"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				sg_t.setSchool(s);
				
				StudentGroup sg = new StudentGroup ();
				sg.setId(rs.getInt("student_group_id"));
				sg_t.setStudent_group_id(sg);
				
				Teacher t = new Teacher ();
				t.setId(rs.getInt("teacher_id"));
				sg_t.setTeacher_id(t);
				
				sg_tList.add (sg_t);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return sg_tList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<StudentGroup_Teacher> fetchBySchool (School school) {
		try {
			List<StudentGroup_Teacher> sg_tList = new ArrayList<StudentGroup_Teacher>();
			
			String sql = "select * from student_group_teacher where sg_t.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup_Teacher sg_t = new StudentGroup_Teacher ();
				sg_t.setId(rs.getInt("student_group_teacher_id"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				sg_t.setSchool(s);
				
				StudentGroup sg = new StudentGroup ();
				sg.setId(rs.getInt("student_group_id"));
				sg_t.setStudent_group_id(sg);
				
				Teacher t = new Teacher ();
				t.setId(rs.getInt("teacher_id"));
				sg_t.setTeacher_id(t);
				
				sg_tList.add (sg_t);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return sg_tList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
