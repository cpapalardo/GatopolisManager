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

@SuppressWarnings("serial")
@Entity
@Table(name="report_teacher")
@NamedQuery(name="ReportTeacher.findByInepCode", query="select rt from ReportTeacher rt WHERE rt.teacher.school.schoolData.inep = :inep and rt.isDeleted = false")
public class ReportTeacher extends JsonBehaviour implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="is_deleted", nullable=false)
	private Boolean isDeleted;
	
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
	
	public ReportTeacher() {}
	
	public ReportTeacher(String json) {
		super.setJson(json);
	}

	public ReportTeacher(Integer dashboard_opened,
			Integer dashboard_duration, Integer aba_obs_duration,
			Integer aba_prod_duration, Integer aba_freq_duration,
			Integer transitions_duration, Integer grouping_duration,
			Date access, Teacher teacher) {
		super();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return super.toString() + "ReportTeacher [dashboard_opened="
				+ dashboard_opened + ", dashboard_duration="
				+ dashboard_duration + ", aba_obs_duration=" + aba_obs_duration
				+ ", aba_prod_duration=" + aba_prod_duration
				+ ", aba_freq_duration=" + aba_freq_duration
				+ ", transitions_duration=" + transitions_duration
				+ ", grouping_duration=" + grouping_duration + ", teacher="
				+ (teacher != null ? teacher.getId() : 0) + ", "
				+ "access=" + df.format(access) + "]";
	}
	
}
