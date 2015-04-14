package br.com.motogatomanager.modelo;

public class ReportTeacher extends Generic {
	private int dashboard_opened;
	private int dashboard_duration;
	private int duration_aba_obs;
	private int duration_aba_prod;
	private int duration_aba_freq;
	private int duration_transitions;
	private int duration_grouping;
	private Teacher teacher;
	private School school;
	
	public ReportTeacher() {
		super();
	}

	public ReportTeacher(int dashboard_opened, int dashboard_duration,
			int duration_aba_obs, int duration_aba_prod, int duration_aba_freq,
			int duration_transitions, int duration_grouping, Teacher teacher,
			School school) {
		super();
		this.dashboard_opened = dashboard_opened;
		this.dashboard_duration = dashboard_duration;
		this.duration_aba_obs = duration_aba_obs;
		this.duration_aba_prod = duration_aba_prod;
		this.duration_aba_freq = duration_aba_freq;
		this.duration_transitions = duration_transitions;
		this.duration_grouping = duration_grouping;
		this.teacher = teacher;
		this.school = school;
	}

	public int getDashboard_opened() {
		return dashboard_opened;
	}

	public void setDashboard_opened(int dashboard_opened) {
		this.dashboard_opened = dashboard_opened;
	}

	public int getDashboard_duration() {
		return dashboard_duration;
	}

	public void setDashboard_duration(int dashboard_duration) {
		this.dashboard_duration = dashboard_duration;
	}

	public int getDuration_aba_obs() {
		return duration_aba_obs;
	}

	public void setDuration_aba_obs(int duration_aba_obs) {
		this.duration_aba_obs = duration_aba_obs;
	}

	public int getDuration_aba_prod() {
		return duration_aba_prod;
	}

	public void setDuration_aba_prod(int duration_aba_prod) {
		this.duration_aba_prod = duration_aba_prod;
	}

	public int getDuration_aba_freq() {
		return duration_aba_freq;
	}

	public void setDuration_aba_freq(int duration_aba_freq) {
		this.duration_aba_freq = duration_aba_freq;
	}

	public int getDuration_transitions() {
		return duration_transitions;
	}

	public void setDuration_transitions(int duration_transitions) {
		this.duration_transitions = duration_transitions;
	}

	public int getDuration_grouping() {
		return duration_grouping;
	}

	public void setDuration_grouping(int duration_grouping) {
		this.duration_grouping = duration_grouping;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return super.toString() + "ReportTeacher [dashboard_opened=" + dashboard_opened
				+ ", dashboard_duration=" + dashboard_duration
				+ ", duration_aba_obs=" + duration_aba_obs
				+ ", duration_aba_prod=" + duration_aba_prod
				+ ", duration_aba_freq=" + duration_aba_freq
				+ ", duration_transitions=" + duration_transitions
				+ ", duration_grouping=" + duration_grouping + ", teacher="
				+ teacher + ", school=" + school + "]";
	}
	
}
