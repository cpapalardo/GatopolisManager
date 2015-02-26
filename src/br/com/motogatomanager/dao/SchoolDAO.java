package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;

public class SchoolDAO {

	private Connection connection;

	public SchoolDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(School school) {
		String sql = "insert into school "
				+ "(name,sync_code,coordinator_code,public_id)" + " values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, school.getName());
			stmt.setString(2, school.getSync_code());
			stmt.setString(3, school.getCoordinator_code());
			stmt.setString(4, school.getPublic_id());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<School> fetchAll () {
		try {
			List<School> schools = new ArrayList<School>();
			
			String sql = "select * from school";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				School school = new School ();
				school.setId(rs.getInt("school_id"));
				school.setName(rs.getString("name"));
				school.setSync_code(rs.getString("sync_code"));
				school.setCoordinator_code(rs.getString("coordinator_code"));
				school.setPublic_id(rs.getString("public_id"));
				
				schools.add (school);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return schools;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public School fetchBySyncCode (String syncCode) {
		try {
			String sql = "select * from school where school.sync_code = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, syncCode);
			
			ResultSet rs = stmt.executeQuery();
			School school = new School ();
			while (rs.next()) {
				school.setId(rs.getInt("school_id"));
				school.setName(rs.getString("name"));
				school.setSync_code(rs.getString("sync_code"));
				school.setCoordinator_code(rs.getString("coordinator_code"));
				school.setPublic_id(rs.getString("public_id"));
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return school;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
