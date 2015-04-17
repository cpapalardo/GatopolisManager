package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.gatopolismanager.item.ReportStudentItem;
import br.com.gatopolismanager.jdbc.ConnectionFactory;

public class ReportStudentDAO {
	private Connection connection;

	public ReportStudentDAO () {
		connection = new ConnectionFactory().getConnection();
	}
	
	public List<ReportStudentItem> getReportStudentItensBySchool (int schoolId) {
		try {
			List<ReportStudentItem> rsiList = new ArrayList<ReportStudentItem>();
			
			String sql = "select "
					+ "st.student_id, "
					+ "st.name as student_name, "
					+ "(select count(rs.date_access) from report_student rs "
					+ "where rs.student_id = st.student_id and rs.date_access between ? and ?) as semana, "
					+ "(select count(rs.date_access) from report_student rs "
					+ "where rs.student_id = st.student_id and rs.date_access between ? and ?) as mes, "
					+ "(select count(rs.date_access) from report_student rs "
					+ "where rs.student_id = st.student_id and rs.date_access between ? and ?) as ano, "
					+ "(select count(*) from viewed_student vs "
					+ "where vs.student_id = st.student_id) as visualizado "
					+ "from student st "
					+ "inner join school s "
					+ "on s.school_id = st.school_id "
					+ "where s.school_id = ? "
					+ "group by s.name, st.student_id, st.name";
			
			Calendar thisWeek1 = Calendar.getInstance();
			Calendar thisWeek2 = Calendar.getInstance();
			Calendar thisMonth1 = Calendar.getInstance();
			Calendar thisMonth2 = Calendar.getInstance();
			Calendar thisYear1 = Calendar.getInstance();
			Calendar thisYear2 = Calendar.getInstance();
			
			thisWeek1.set(Calendar.DAY_OF_WEEK, thisWeek1.getActualMinimum(Calendar.DAY_OF_WEEK));
			thisWeek2.set(Calendar.DAY_OF_WEEK, thisWeek2.getActualMaximum(Calendar.DAY_OF_WEEK));
			thisMonth1.set(Calendar.DAY_OF_MONTH, thisMonth1.getActualMinimum(Calendar.DAY_OF_MONTH));
			thisMonth2.set(Calendar.DAY_OF_MONTH, thisMonth2.getActualMaximum(Calendar.DAY_OF_MONTH));
			thisYear1.set(Calendar.DAY_OF_YEAR, thisYear1.getActualMinimum(Calendar.DAY_OF_YEAR));
			thisYear2.set(Calendar.DAY_OF_YEAR, thisYear2.getActualMaximum(Calendar.DAY_OF_YEAR));
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setDate(1, new Date(thisWeek1.getTimeInMillis()));
			stmt.setDate(2, new Date(thisWeek2.getTimeInMillis()));
			stmt.setDate(3, new Date(thisMonth1.getTimeInMillis()));
			stmt.setDate(4, new Date(thisMonth2.getTimeInMillis()));
			stmt.setDate(5, new Date(thisYear1.getTimeInMillis()));
			stmt.setDate(6, new Date(thisYear2.getTimeInMillis()));
			
			stmt.setInt(7, schoolId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ReportStudentItem rsi = new ReportStudentItem ();
				rsi.setStudentName(rs.getString("student_name"));
				rsi.setAccessedThisWeek(rs.getInt("semana"));
				rsi.setAccessedThisMonth(rs.getInt("mes"));
				rsi.setAccessedThisYear(rs.getInt("ano"));
				rsi.setViewedCount(rs.getInt("visualizado"));
				
				rsiList.add (rsi);
			}
			rs.close();
			stmt.close();
			connection.close();
			
			return rsiList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
