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

public class StudentGroupDAO {
	private Connection connection;

	public StudentGroupDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(StudentGroup group) {
		
		String sql = "insert into student_group "
				+ "(name,series,period,school_id)" 
				+ " values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, group.getName());
			stmt.setString(2, group.getSeries());
			stmt.setString(3, group.getPeriod());
			stmt.setInt(4, group.getSchool().getId());

			stmt.execute();
			
			String lastId = "select LAST_INSERT_ID()";
			stmt = connection.prepareStatement(lastId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				group.setId(rs.getInt("LAST_INSERT_ID()"));
			}
			rs.close();
			stmt.close();
			
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(StudentGroup group) {
		String sql = "update student_group "
				+ "set name = ?,series = ?,period = ?,school_id = ?" 
				+ "where student_group.student_group_id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, group.getName());
			stmt.setString(2, group.getSeries());
			stmt.setString(3, group.getPeriod());
			stmt.setInt(4, group.getSchool().getId());
			stmt.setInt(5, group.getId());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<StudentGroup> fetchAll () {
		try {
			List<StudentGroup> groups = new ArrayList<StudentGroup>();
			
			String sql = "select * from student_group";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup group = new StudentGroup ();
				group.setId(rs.getInt("student_group_id"));
				group.setName(rs.getString("name"));
				group.setSeries(rs.getString("series"));
				group.setPeriod(rs.getString("period"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				group.setSchool(s);
				
				groups.add (group);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return groups;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<StudentGroup> fetchBySchool (School school) {
		try {
			List<StudentGroup> groups = new ArrayList<StudentGroup>();
			
			String sql = "select * from student_group where student_group.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup group = new StudentGroup ();
				group.setId(rs.getInt("student_group_id"));
				group.setName(rs.getString("name"));
				group.setSeries(rs.getString("series"));
				group.setPeriod(rs.getString("period"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				group.setSchool(s);
				
				groups.add (group);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return groups;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public StudentGroup fetchBySchoolAndPeriodAndSeries (School school, String period, String series) {
		try {
			StudentGroup group = new StudentGroup();
			
			String sql = "select * from student_group where student_group.school_id = ? and student_group.period = ? and student_group.series = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			stmt.setString(2, period);
			stmt.setString(3, series);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				group.setId(rs.getInt("student_group_id"));
				group.setName(rs.getString("name"));
				group.setSeries(rs.getString("series"));
				group.setPeriod(rs.getString("period"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				group.setSchool(s);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return group;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public StudentGroup fetchById (int id) {
		try {
			String sql = "select * from student_group where student_group.student_group_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			StudentGroup group = new StudentGroup ();
			while (rs.next()) {
				group.setId(rs.getInt("student_group_id"));
				group.setName(rs.getString("name"));
				group.setSeries(rs.getString("series"));
				group.setPeriod(rs.getString("period"));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				group.setSchool(s);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return group;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
