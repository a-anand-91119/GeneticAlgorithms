package com.anand.geneticalgorithm.domain;

import java.util.List;

/**
 * 
 * @author A Anand
 *
 */
public class Department {

	private String departmentName;
	private List<Subject> subjects;

	public Department() {
		super();
	}

	public Department(String departmentName, List<Subject> subjects) {
		super();
		this.departmentName = departmentName;
		this.subjects = subjects;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Department [departmentName=" + departmentName + ", subjects=" + subjects + "]";
	}

}
