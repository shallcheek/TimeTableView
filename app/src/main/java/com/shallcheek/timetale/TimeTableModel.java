package com.shallcheek.timetale;

public class TimeTableModel {
	private int id;
	private int startnum;
	private int endnum;
	private int week;
	private String starttime="";
	private String endtime="";
	private String name="";
	private String teacher="";
	private String classroom="";
	private String weeknum="";

	@Override
	public String toString() {
		return "TimeTableModel [id=" + id + ", startnum=" + startnum
				+ ", endnum=" + endnum + ", week=" + week + ", starttime="
				+ starttime + ", endtime=" + endtime + ", name=" + name
				+ ", teacher=" + teacher + ", classroom=" + classroom
				+ ", weeknum=" + weeknum + "]";
	}

	public int getId() {
		return id;
	}

	public int getStartnum() {
		return startnum;
	}

	public int getEndnum() {
		return endnum;
	}

	public int getWeek() {
		return week;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getName() {
		return name;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getClassroom() {
		return classroom;
	}

	public String getWeeknum() {
		return weeknum;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	public void setEndnum(int endnum) {
		this.endnum = endnum;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public void setWeeknum(String weeknum) {
		this.weeknum = weeknum;
	}
	public TimeTableModel() {
		// TODO Auto-generated constructor stub
	}

	public TimeTableModel(int id, int startnum, int endnum, int week,
			String starttime, String endtime, String name, String teacher,
			String classroom, String weeknum) {
		super();
		this.id = id;
		this.startnum = startnum;
		this.endnum = endnum;
		this.week = week;
		this.starttime = starttime;
		this.endtime = endtime;
		this.name = name;
		this.teacher = teacher;
		this.classroom = classroom;
		this.weeknum = weeknum;
	}

}
