package com.anand.geneticalgorithm.driver;

import java.util.List;

import com.anand.geneticalgorithm.algorithm.DataProvider;
import com.anand.geneticalgorithm.algorithm.GeneticAlgorithm;
import com.anand.geneticalgorithm.algorithm.Population;
import com.anand.geneticalgorithm.algorithm.Schedule;
import com.anand.geneticalgorithm.domain.Class;

/**
 * 
 * @author A Anand
 *
 */
public class MainApp {

	public static final int POPULATION_SIZE = 9;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.5;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUMBER_OF_ELITE_SCHEDULES = 1;
	private DataProvider dataProvider;
	private int scheduleNumber = 0;
	private int classNumber = 0;

	public static void main(String[] args) {

		MainApp mainApp = new MainApp();
		mainApp.dataProvider = new DataProvider();
		mainApp.printAvailableData();

		int generationNumber = 0;
		mainApp.scheduleNumber = 0;
		System.out.println("Generation #" + generationNumber);
		System.out.println("Schedule # | Classes [Dept,class,room,teacher,timeslot] | Fitness | Conflicts");
		System.out.print(
				"------------------------------------------------------------------------------------------------");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(mainApp.dataProvider);
		Population population = new Population(MainApp.POPULATION_SIZE, mainApp.dataProvider).sortPopulationByFitness();
		population.getSchedules().forEach(x -> System.out.println("    " + (mainApp.scheduleNumber++) + "      | " + x
				+ "  |  " + String.format("%.5f", x.getFitness()) + "  |  " + x.getNumberOfConflicts()));
		mainApp.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
		mainApp.classNumber = 1;
		while (population.getSchedules().get(0).getFitness() != 1.0) {
			System.out.println("Generation #" + generationNumber++);
			System.out.println("Schedule # | Classes [Dept,class,room,teacher,timeslot] | Fitness | Conflicts");
			System.out.print(
					"------------------------------------------------------------------------------------------------");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			population = geneticAlgorithm.evolvePopulation(population).sortPopulationByFitness();
			mainApp.scheduleNumber = 0;
			population.getSchedules().forEach(x -> System.out.println("    " + (mainApp.scheduleNumber++) + "      | "
					+ x + "  |  " + String.format("%.5f", x.getFitness()) + "  |  " + x.getNumberOfConflicts()));
			mainApp.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
			mainApp.classNumber = 1;
		}
	}

	private void printAvailableData() {
		System.out.println("Available Departments ==> ");
		dataProvider.getDepartments()
				.forEach(x -> System.out.println("Name: " + x.getDepartmentName() + " Subjects: " + x.getSubjects()));
		System.out.println("\nAvailable Subject ==> ");
		dataProvider.getSubjects()
				.forEach(x -> System.out.println("Subject #:" + x.getCourseId() + " Name: " + x.getCourseName()
						+ " Max no of Students: " + x.getMaxNumberOfStudents() + " Teachers: " + x.getTeachers()));
		System.out.println("\nAvailable Rooms ==> ");
		dataProvider.getRooms().forEach(x -> System.out
				.println("Room #:" + x.getRoomNumber() + " Max Seating Capacity: " + x.getRoomSeatingCapacity()));
		System.out.println("\nAvailable Teachers ==> ");
		dataProvider.getTeachers()
				.forEach(x -> System.out.println("Id: " + x.getTeacherId() + " Name: " + x.getTeacherName()));
		System.out.println("\nAvailable TimeSlots ==> ");
		dataProvider.getTimeslots()
				.forEach(x -> System.out.println("Id: " + x.getTimeSlotId() + " Time: " + x.getTimeSlotTime()));
		System.out.print(
				"------------------------------------------------------------------------------------------------");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
	}

	private void printScheduleAsTable(Schedule schedule, int generation) {
		List<Class> classes = schedule.getClasses();
		System.out.print("\n                    ");
		System.out.println(
				"Class # | Dept | Subject (id, max # of students) | Room (capacity) | Teacher (Id) | TimeSlot (Id)");
		System.out.print(
				"------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
		classes.forEach(x -> {
			System.out.print("\n                    ");
			System.out.print(String.format(" %1$02d  ", classNumber) + "  |  ");
			System.out.print(String.format("%1$4s", x.getDepartment().getDepartmentName()) + " |  ");
			System.out.print(String.format("%1$21s", x.getSubject().getCourseName() + " ("
					+ x.getSubject().getCourseId() + ", " + x.getSubject().getMaxNumberOfStudents()) + ") | ");
			System.out.print(
					String.format("%1$10s", x.getRoom().getRoomNumber() + " (" + x.getRoom().getRoomSeatingCapacity())
							+ ") | ");
			System.out.print(
					String.format("%1$15s", x.getTeacher().getTeacherName() + " (" + x.getTeacher().getTeacherId())
							+ ")   |   ");
			System.out.print(x.getDepartment().getDepartmentName() + "  |  ");
			classNumber++;
		});
		if (schedule.getFitness() == 1) {
			System.out.println("\n>>>> Solution Found In " + (generation + 1) + " generations");
		}
		System.out.print(
				"\n------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------");
	}

}
