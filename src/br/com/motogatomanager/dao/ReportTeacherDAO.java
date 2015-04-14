package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gatopolismanager.item.ReportTeacherItem;
import br.com.gatopolismanager.jdbc.ConnectionFactory;
import br.com.motogatomanager.modelo.School;

public class ReportTeacherDAO {
	private Connection connection;

	public ReportTeacherDAO () {
		connection = new ConnectionFactory().getConnection();
	}
	
	public List<ReportTeacherItem> getReportTeacherItensBySchool (School school) {
		if (school == null)
			return null;
		
		try {
			List<ReportTeacherItem> rtiList = new ArrayList<ReportTeacherItem>();
			
			String sql = "select "
					+ "s.school_id, "
					+ "t.teacher_id, "
					+ "t.name as teacher_name, "
					+ "sum (rt.dashboard_opened) as opened, "
					+ "sum (rt.dashboard_duration) as duration, "
					+ "sum (rt.duration_aba_obs) as obs, "
					+ "sum (rt.duration_aba_prod) as prod, "
					+ "sum (rt.duration_aba_freq) as freq, "
					+ "sum (rt.duration_transitions) as trans, "
					+ "sum (rt.duration_grouping) as grouping "
					+ "from teacher t "
					+ "inner join school s "
					+ "on s.school_id = t.school_id "
					+ "left join report_teacher rt "
					+ "on rt.teacher_id = t.teacher_id "
					+ "where s.school_id = ? "
					+ "group by s.school_id, s.name, t.teacher_id, t.name";
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, school.getId());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ReportTeacherItem rti = new ReportTeacherItem ();
				rti.setTeacherName(rs.getString("teacher_name"));
				
				rti.setDashboardOpenedTotal (rs.getInt("opened"));
				rti.setDashboardDurationTotal (rs.getInt("duration"));
				rti.setDurationAbaObsTotal (rs.getInt("obs"));
				rti.setDurationAbaProdTotal (rs.getInt("prod"));
				rti.setDurationAbaFreqTotal (rs.getInt("freq"));
				rti.setDurationTransitionsTotal (rs.getInt("trans"));
				rti.setDurationGroupingTotal (rs.getInt("grouping"));
				
				rtiList.add (rti);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return rtiList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
