package br.com.farofa.gm.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONObject;

@Entity
@Table(name="report_teacher")
@NamedQuery(name="ReportTeacher.findByInepCode", query="select rt from ReportTeacher rt WHERE rt.teacher.school.schoolData.inep = :inep")
public class ReportTeacher implements Serializable, JsonBehaviour {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=true)
	private Integer dashboard_opened;
	
	@Column(nullable=true)
	private Integer dashboard_duration;
	
	@Column(nullable=true)
	private Integer aba_obs_duration;
	
	@Column(nullable=true)
	private Integer aba_prod_duration;
	
	@Column(nullable=true)
	private Integer aba_freq_duration;
	
	@Column(nullable=true)
	private Integer transitions_duration;
	
	@Column(nullable=true)
	private Integer grouping_duration;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date access;
	
	@ManyToOne
	@JoinColumn(name="teacher_id", nullable=false)
	private Teacher teacher;
	
	public ReportTeacher() {
		super();
	}

	public ReportTeacher(Integer id, Integer dashboard_opened,
			Integer dashboard_duration, Integer aba_obs_duration,
			Integer aba_prod_duration, Integer aba_freq_duration,
			Integer transitions_duration, Integer grouping_duration,
			Date access, Teacher teacher) {
		super();
		this.id = id;
		this.dashboard_opened = dashboard_opened;
		this.dashboard_duration = dashboard_duration;
		this.aba_obs_duration = aba_obs_duration;
		this.aba_prod_duration = aba_prod_duration;
		this.aba_freq_duration = aba_freq_duration;
		this.transitions_duration = transitions_duration;
		this.grouping_duration = grouping_duration;
		this.access = access;
		this.teacher = teacher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDashboard_opened() {
		return dashboard_opened;
	}

	public void setDashboard_opened(Integer dashboard_opened) {
		this.dashboard_opened = dashboard_opened;
	}

	public Integer getDashboard_duration() {
		return dashboard_duration;
	}

	public void setDashboard_duration(Integer dashboard_duration) {
		this.dashboard_duration = dashboard_duration;
	}

	public Integer getAba_obs_duration() {
		return aba_obs_duration;
	}

	public void setAba_obs_duration(Integer aba_obs_duration) {
		this.aba_obs_duration = aba_obs_duration;
	}

	public Integer getAba_prod_duration() {
		return aba_prod_duration;
	}

	public void setAba_prod_duration(Integer aba_prod_duration) {
		this.aba_prod_duration = aba_prod_duration;
	}

	public Integer getAba_freq_duration() {
		return aba_freq_duration;
	}

	public void setAba_freq_duration(Integer aba_freq_duration) {
		this.aba_freq_duration = aba_freq_duration;
	}

	public Integer getTransitions_duration() {
		return transitions_duration;
	}

	public void setTransitions_duration(Integer transitions_duration) {
		this.transitions_duration = transitions_duration;
	}

	public Integer getGrouping_duration() {
		return grouping_duration;
	}

	public void setGrouping_duration(Integer grouping_duration) {
		this.grouping_duration = grouping_duration;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Date getAccess() {
		return access;
	}

	public void setAccess(Date access) {
		this.access = access;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return "ReportTeacher [id=" + id + ", dashboard_opened="
				+ dashboard_opened + ", dashboard_duration="
				+ dashboard_duration + ", aba_obs_duration=" + aba_obs_duration
				+ ", aba_prod_duration=" + aba_prod_duration
				+ ", aba_freq_duration=" + aba_freq_duration
				+ ", transitions_duration=" + transitions_duration
				+ ", grouping_duration=" + grouping_duration + ", teacher="
				+ (teacher != null ? teacher.getId() : 0) + ", "
				+ "access=" + df.format(access) + "]";
	}
	
	@Override
	public String getJson() {
		JSONObject jsonObj = new JSONObject();
		if (id != null) jsonObj.put("id", id);
		if (dashboard_opened != null) jsonObj.put("dashboard_opened", dashboard_opened);
		if (dashboard_duration != null) jsonObj.put("dashboard_duration", dashboard_duration);
		if (aba_obs_duration != null) jsonObj.put("aba_obs_duration", aba_obs_duration);
		if (aba_prod_duration != null) jsonObj.put("aba_prod_duration", aba_prod_duration);
		if (aba_freq_duration != null) jsonObj.put("aba_freq_duration", aba_freq_duration);
		if (transitions_duration != null) jsonObj.put("transitions_duration", transitions_duration);
		if (grouping_duration != null) jsonObj.put("grouping_duration", grouping_duration);
		if (teacher != null && teacher.getId() != null) jsonObj.put("teacher", teacher);
		return jsonObj.toString();
	}

	@Override
	public void setJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj.has("id")) id = jsonObj.getInt("id");
		if (jsonObj.has("dashboard_opened")) dashboard_opened = jsonObj.getInt("dashboard_opened");
		if (jsonObj.has("dashboard_duration")) dashboard_duration = jsonObj.getInt("dashboard_duration");
		if (jsonObj.has("aba_obs_duration")) aba_obs_duration = jsonObj.getInt("aba_obs_duration");
		if (jsonObj.has("aba_prod_duration")) aba_prod_duration = jsonObj.getInt("aba_prod_duration");
		if (jsonObj.has("aba_freq_duration")) aba_freq_duration = jsonObj.getInt("aba_freq_duration");
		if (jsonObj.has("transitions_duration")) transitions_duration = jsonObj.getInt("transitions_duration");
		if (jsonObj.has("grouping_duration")) grouping_duration = jsonObj.getInt("grouping_duration");
		if (jsonObj.has("teacher")) {
			teacher = new Teacher();
			int id = jsonObj.getInt("teacher");
			teacher.setId(id);
		}
	}
}
