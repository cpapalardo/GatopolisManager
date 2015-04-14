package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.modelo.Student;
import br.com.motogatomanager.modelo.Teacher;
import br.com.motogatomanager.modelo.ViewedStudent;

public class ViewedStudentDAO {
	private Connection connection;

	public ViewedStudentDAO () {
		connection = new ConnectionFactory().getConnection();
	}
	
	public List<ViewedStudent> fetchAll () {
		try {
			List<ViewedStudent> vsList = new ArrayList<ViewedStudent>();
			
			String sql = "select * from teacher";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ViewedStudent vs = new ViewedStudent ();
				vs.setId(rs.getInt("teacher_id"));
				
				Teacher t = new Teacher();
				t.setId (rs.getInt("teacher_id"));
				vs.setTeacher (t);
				
				Student student = new Student ();
				student.setId(rs.getInt("student_id"));
				vs.setStudent(student);
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				vs.setSchool(s);
				
				vsList.add (vs);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return vsList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
