package com.anand.geneticalgorithm.domain;

import java.util.List;

/**
 * 
 * @author A Anand
 *
 */
public class Subject {

	private String courseId;
	private String courseName;
	private int maxNumberOfStudents;
	private List<Teacher> teachers;

	public Subject() {
		super();
	}

	public Subject(String courseId, String courseName, int maxNumberOfStudents, List<Teacher> teachers) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.maxNumberOfStudents = maxNumberOfStudents;
		this.teachers = teachers;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getMaxNumberOfStudents() {
		return maxNumberOfStudents;
	}

	public void setMaxNumberOfStudents(int maxNumberOfStudents) {
		this.maxNumberOfStudents = maxNumberOfStudents;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "courseId=" + courseId + ", courseName=" + courseName + ", maxNumberOfStudents="
				+ maxNumberOfStudents + ", teachers= " + teachers;
	}

}
