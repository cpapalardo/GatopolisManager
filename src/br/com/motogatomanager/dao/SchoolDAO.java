package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;
import br.com.motogatomanager.util.EncodingUtil;

public class SchoolDAO {

	private Connection connection;

	public SchoolDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void save(School school) {
		//Gera um novo sync code
		try {
			String sql = "select IDENT_CURRENT('school') + IDENT_INCR('school') as last_id";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			
			String lastId = null;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lastId = String.valueOf(rs.getInt("last_id"));
			}
			rs.close();
			stmt.close();
			
			int max = (int) Math.pow(10, lastId.length());
			String syncCode = String.valueOf (new Random ().nextInt(max));
			if (syncCode.length() < lastId.length()) {
				syncCode = "0" + syncCode;
			}
			syncCode = syncCode + lastId;
			
			//Setting the new Sync Code in school object
			school.setSync_code(syncCode);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			String sql = "insert into school "
					+ "(name,sync_code)" + " values (?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, EncodingUtil.ConvertToISO (school.getName()));//TODO encoding
			stmt.setString(2, school.getSync_code());

			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				school.setId(rs.getInt(1));
			}
			rs.close();
			
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
				school.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				school.setSync_code(rs.getString("sync_code"));
				
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
				school.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				school.setSync_code(rs.getString("sync_code"));
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return school;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public School fetchByName (String name) {
		try {
			String sql = "select * from school where school.name = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, EncodingUtil.ConvertToISO(name));//TODO encoding
			
			ResultSet rs = stmt.executeQuery();
			School school = new School ();
			while (rs.next()) {
				school.setId(rs.getInt("school_id"));
				school.setName(EncodingUtil.ConvertToUTF8(rs.getString("name")));//TODO encoding
				school.setSync_code(rs.getString("sync_code"));
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
