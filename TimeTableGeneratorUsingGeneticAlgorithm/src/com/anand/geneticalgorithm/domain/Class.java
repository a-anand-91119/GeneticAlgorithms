package com.anand.geneticalgorithm.domain;

/**
 * 
 * @author A Anand
 *
 */
public class Class {

	private int classId;
	private Department department;
	private Subject subject;
	private Teacher teacher;
	private TimeSlot timeSlot;
	private Room room;

	public Class() {
		super();
	}

	public Class(int classId, Department department, Subject subject) {
		super();
		this.classId = classId;
		this.department = department;
		this.subject = subject;
	}

	public Class(int classId, Department department, Subject subject, Teacher teacher, TimeSlot timeSlot, Room room) {
		super();
		this.classId = classId;
		this.department = department;
		this.subject = subject;
		this.teacher = teacher;
		this.timeSlot = timeSlot;
		this.room = room;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "[" + department.getDepartmentName() + "," + subject.getCourseId() + ","
				+ teacher.getTeacherId() + "," + timeSlot.getTimeSlotId() + "," + room.getRoomNumber() + "]";
	}

}
