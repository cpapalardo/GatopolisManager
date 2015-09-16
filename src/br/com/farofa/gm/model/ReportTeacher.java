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
	
	@Column(nullable=true, name="dashboard_opened")
	private Integer dashboardOpened;
	
	@Column(nullable=true, name="dashboard_duration")
	private Integer dashboardDuration;
	
	@Column(nullable=true, name="aba_obs_duration")
	private Integer abaObsDuration;
	
	@Column(nullable=true, name="aba_prod_duration")
	private Integer abaProdDuration;
	
	@Column(nullable=true, name="aba_freq_duration")
	private Integer abaFreqDuration;
	
	@Column(nullable=true, name="transitions_duration")
	private Integer transitionsDuration;
	
	@Column(nullable=true, name="grouping_duration")
	private Integer groupingDuration;
	
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
		this.dashboardOpened = dashboard_opened;
		this.dashboardDuration = dashboard_duration;
		this.abaObsDuration = aba_obs_duration;
		this.abaProdDuration = aba_prod_duration;
		this.abaFreqDuration = aba_freq_duration;
		this.transitionsDuration = transitions_duration;
		this.groupingDuration = grouping_duration;
		this.access = access;
		this.teacher = teacher;
	}

	public Integer getDashboard_opened() {
		return dashboardOpened;
	}

	public void setDashboard_opened(Integer dashboard_opened) {
		this.dashboardOpened = dashboard_opened;
	}

	public Integer getDashboard_duration() {
		return dashboardDuration;
	}

	public void setDashboard_duration(Integer dashboard_duration) {
		this.dashboardDuration = dashboard_duration;
	}

	public Integer getAba_obs_duration() {
		return abaObsDuration;
	}

	public void setAba_obs_duration(Integer aba_obs_duration) {
		this.abaObsDuration = aba_obs_duration;
	}

	public Integer getAba_prod_duration() {
		return abaProdDuration;
	}

	public void setAba_prod_duration(Integer aba_prod_duration) {
		this.abaProdDuration = aba_prod_duration;
	}

	public Integer getAba_freq_duration() {
		return abaFreqDuration;
	}

	public void setAba_freq_duration(Integer aba_freq_duration) {
		this.abaFreqDuration = aba_freq_duration;
	}

	public Integer getTransitions_duration() {
		return transitionsDuration;
	}

	public void setTransitions_duration(Integer transitions_duration) {
		this.transitionsDuration = transitions_duration;
	}

	public Integer getGrouping_duration() {
		return groupingDuration;
	}

	public void setGrouping_duration(Integer grouping_duration) {
		this.groupingDuration = grouping_duration;
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
				+ dashboardOpened + ", dashboard_duration="
				+ dashboardDuration + ", aba_obs_duration=" + abaObsDuration
				+ ", aba_prod_duration=" + abaProdDuration
				+ ", aba_freq_duration=" + abaFreqDuration
				+ ", transitions_duration=" + transitionsDuration
				+ ", grouping_duration=" + groupingDuration + ", teacher="
				+ (teacher != null ? teacher.getId() : 0) + ", "
				+ "access=" + df.format(access) + "]";
	}
	
}
