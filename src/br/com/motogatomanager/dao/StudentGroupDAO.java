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
import br.com.motogatomanager.modelo.StudentGroup;
import br.com.motogatomanager.modelo.Teacher;

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
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, (group.getName()));
			stmt.setString(2, (group.getSeries()));
			stmt.setString(3, (group.getPeriod()));
			stmt.setInt(4, group.getSchool().getId());

			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				group.setId(rs.getInt(1));
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

			stmt.setString(1, (group.getName()));
			stmt.setString(2, (group.getSeries()));
			stmt.setString(3, (group.getPeriod()));
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
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
				
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
			
			String sql = "select sg.*, "
					+ " (select count(*) from student st where st.student_group_id = sg.student_group_id) as alunos "
					+ " from student_group sg "
					+ " where sg.school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup group = new StudentGroup ();
				group.setId(rs.getInt("student_group_id"));
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
				
				School s = new School ();
				s.setId(rs.getInt("school_id"));
				group.setSchool(s);
				
				group.setQtdeAlunos(rs.getInt("alunos"));  
				
				//Inner join professores das turmas
				String subSQL = "select t.name as teacher_name"
						+ " from teacher t"
						+ " inner join student_group_teacher sgt"
						+ " on sgt.teacher_id = t.teacher_id"
						+ " where sgt.student_group_id = ?";
				PreparedStatement subSTMT = this.connection.prepareStatement(subSQL);
				subSTMT.setInt(1, group.getId());
				ResultSet subRS = subSTMT.executeQuery();
				String teachers = "";
				while (subRS.next()) {
					teachers += (subRS.getString("teacher_name"));
					teachers += " ";
				}
				group.setProfessores(teachers.trim());
				//end
				
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
			stmt.setString(2, (period));
			stmt.setString(3, (series));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				group.setId(rs.getInt("student_group_id"));
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
				
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
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
				
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

	public StudentGroup fetchByNameAndSerieAndSchool (String name, String serie, School school) {
		try {
			String sql = "select * from student_group where student_group.name = ? and student_group.series = ? and school_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, (name));
			stmt.setString(2, (serie));
			stmt.setInt(3, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			StudentGroup group = new StudentGroup ();
			while (rs.next()) {
				group.setId(rs.getInt("student_group_id"));
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return group;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<StudentGroup> fetchByTeacher(Teacher teacher) {
		try {
			List<StudentGroup> groups = new ArrayList<StudentGroup>();
			
			String sql = "select sg.* from student_group sg"
					+ " inner join student_group_teacher sgt"
					+ " on sgt.student_group_id = sg.student_group_id"
					+ " where sgt.teacher_id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, teacher.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StudentGroup group = new StudentGroup ();
				group.setId(rs.getInt("student_group_id"));
				group.setName((rs.getString("name")));
				group.setSeries((rs.getString("series")));
				group.setPeriod((rs.getString("period")));
				
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
	
}
