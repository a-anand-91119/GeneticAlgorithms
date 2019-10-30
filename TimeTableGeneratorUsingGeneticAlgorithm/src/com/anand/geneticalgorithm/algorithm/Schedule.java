package com.anand.geneticalgorithm.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.anand.geneticalgorithm.domain.Class;
import com.anand.geneticalgorithm.domain.Department;

/**
 * 
 * @author A Anand
 *
 */
public class Schedule {

	private List<Class> classes;
	private DataProvider dataProvider;
	private int classNumber;
	private int numberOfConflicts;
	private double fitness;
	private boolean isFitnessChanged = true;

	public Schedule(DataProvider dataProvider) {
		super();
		this.dataProvider = dataProvider;
		classes = new ArrayList<Class>(dataProvider.getNumberOfClasses());
	}

	public Schedule initialize() {
		new ArrayList<Department>(dataProvider.getDepartments()).forEach(dept -> {
			dept.getSubjects().forEach(subject -> {
				Class newClass = new Class(classNumber++, dept, subject);
				newClass.setTimeSlot(dataProvider.getTimeslots()
						.get((int) ((getDataProvider().getTimeslots()).size() * Math.random())));
				newClass.setRoom(
						dataProvider.getRooms().get((int) ((getDataProvider().getRooms()).size() * Math.random())));
				newClass.setTeacher(dataProvider.getTeachers()
						.get((int) ((getDataProvider().getTeachers()).size() * Math.random())));
				classes.add(newClass);
			});
		});
		return this;
	}

	private double reCalculateFitness() {
		numberOfConflicts = 0;
		classes.forEach(c1 -> {
			if (c1.getRoom().getRoomSeatingCapacity() < c1.getSubject().getMaxNumberOfStudents())
				numberOfConflicts++;
			classes.stream().filter(c2 -> classes.indexOf(c2) >= classes.indexOf(c1)).forEach(c2 -> {
				if (c1.getTimeSlot() == c2.getTimeSlot() && c1.getClassId() != c2.getClassId()) {
					if (c1.getRoom() == c2.getRoom())
						numberOfConflicts++;
					if (c1.getTeacher() == c2.getTeacher())
						numberOfConflicts++;
				}
			});
		});
		return 1 / (double) (numberOfConflicts + 1);
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public int getNumberOfConflicts() {
		return numberOfConflicts;
	}

	public void setNumberOfConflicts(int numberOfConflicts) {
		this.numberOfConflicts = numberOfConflicts;
	}

	public double getFitness() {
		if (isFitnessChanged) {
			isFitnessChanged = false;
			fitness = reCalculateFitness();
		}
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public boolean isFitnessChanged() {
		return isFitnessChanged;
	}

	public void setFitnessChanged(boolean isFitnessChanged) {
		this.isFitnessChanged = isFitnessChanged;
	}

	public List<Class> getClasses() {
		isFitnessChanged = true;
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	@Override
	public String toString() {
		String returnValue = "";
		for(int i =0;i<classes.size();i++) {
			returnValue += classes.get(i) + ",";
		}
		return returnValue;
	}

}
