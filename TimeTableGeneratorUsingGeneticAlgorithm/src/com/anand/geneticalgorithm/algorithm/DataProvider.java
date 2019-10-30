package com.anand.geneticalgorithm.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.anand.geneticalgorithm.domain.*;

/**
 * This is the Data Provider class. Ideally this should be a DAO
 * 
 * @author A Anand
 *
 */
public class DataProvider {

	private List<Room> rooms;
	private List<Teacher> teachers;
	private List<Subject> subjects;
	private List<Department> departments;
	private List<TimeSlot> timeslots;
	private int numberOfClasses = 0;

	public DataProvider() {
		super();
		initialize();
	}

	private DataProvider initialize() {

		Room r1 = new Room("R1", 100);
		Room r2 = new Room("R2", 75);
		Room r3 = new Room("R3", 80);
		rooms = new ArrayList<Room>(Arrays.asList(r1, r2, r3));

		TimeSlot ts1 = new TimeSlot("TS1", "M 9:00 - 9:30");
		TimeSlot ts2 = new TimeSlot("TS2", "M 9:30 - 10:00");
		TimeSlot ts3 = new TimeSlot("TS3", "M 10:00 - 10:30");
		TimeSlot ts4 = new TimeSlot("TS4", "M 10:30 - 11:00");
		timeslots = new ArrayList<>(Arrays.asList(ts1, ts2, ts3, ts4));

		Teacher t1 = new Teacher("T1", "Teacher 1");
		Teacher t2 = new Teacher("T2", "Teacher 2");
		Teacher t3 = new Teacher("T3", "Teacher 3");
		Teacher t4 = new Teacher("T4", "Teacher 4");
		teachers = new ArrayList<Teacher>(Arrays.asList(t1, t2, t3, t4));

		Subject s1 = new Subject("S1", "Subject 1", 75, new ArrayList<Teacher>(Arrays.asList(t1, t2)));
		Subject s2 = new Subject("S2", "Subject 2", 25, new ArrayList<Teacher>(Arrays.asList(t1, t2, t3)));
		Subject s3 = new Subject("S3", "Subject 3", 100, new ArrayList<Teacher>(Arrays.asList(t1, t2)));
		Subject s4 = new Subject("S4", "Subject 4", 65, new ArrayList<Teacher>(Arrays.asList(t3, t4)));
		Subject s5 = new Subject("S5", "Subject 5", 90, new ArrayList<Teacher>(Arrays.asList(t4)));
		Subject s6 = new Subject("S6", "Subject 6", 70, new ArrayList<Teacher>(Arrays.asList(t1, t3)));
		Subject s7 = new Subject("S7", "Subject 7", 80, new ArrayList<Teacher>(Arrays.asList(t2, t4)));
		subjects = new ArrayList<Subject>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));

		Department d1 = new Department("Dept1", new ArrayList<Subject>(Arrays.asList(s1, s3)));
		Department d2 = new Department("Dept2", new ArrayList<Subject>(Arrays.asList(s2, s4, s5)));
		Department d3 = new Department("Dept3", new ArrayList<Subject>(Arrays.asList(s6, s7)));
		departments = new ArrayList<Department>(Arrays.asList(d1, d2, d3));
		departments.forEach(x -> numberOfClasses += x.getSubjects().size());

		return this;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<TimeSlot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<TimeSlot> timeslots) {
		this.timeslots = timeslots;
	}

	public int getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

}
