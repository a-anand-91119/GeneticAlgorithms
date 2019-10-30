package com.anand.geneticalgorithm.domain;

/**
 * 
 * @author A Anand
 *
 */
public class Teacher {
	private String teacherId;
	private String teacherName;

	public Teacher() {
		super();
	}

	public Teacher(String teacherId, String teacherName) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "teacherId=" + teacherId + ", teacherName=" + teacherName;
	}

}
