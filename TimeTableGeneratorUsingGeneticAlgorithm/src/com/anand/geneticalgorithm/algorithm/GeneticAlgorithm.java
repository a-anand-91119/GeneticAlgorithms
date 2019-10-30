package com.anand.geneticalgorithm.algorithm;

import java.util.List;
import java.util.stream.IntStream;

import com.anand.geneticalgorithm.driver.MainApp;

/**
 * 
 * @author A Anand
 *
 */
public class GeneticAlgorithm {
	private DataProvider dataProvider;

	public GeneticAlgorithm(DataProvider dataprovider) {
		super();
		this.dataProvider = dataprovider;
	}

	public Population evolvePopulation(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getSchedules().size(), this.dataProvider);

		IntStream.range(0, MainApp.NUMBER_OF_ELITE_SCHEDULES)
				.forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));

		IntStream.rangeClosed(MainApp.NUMBER_OF_ELITE_SCHEDULES, population.getSchedules().size() - 1).forEach(x -> {
			if (MainApp.CROSSOVER_RATE > Math.random()) {
				Schedule s1 = selectTournamentPopulation(population).sortPopulationByFitness().getSchedules().get(0);
				Schedule s2 = selectTournamentPopulation(population).sortPopulationByFitness().getSchedules().get(0);
				crossoverPopulation.getSchedules().set(x, crossoverSchedule(s1, s2));
			} else {
				crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
			}
		});

		return crossoverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getSchedules().size(), this.dataProvider);
		List<Schedule> mutateSchedules = mutatePopulation.getSchedules();

		IntStream.range(0, MainApp.NUMBER_OF_ELITE_SCHEDULES)
				.forEach(x -> mutateSchedules.set(x, population.getSchedules().get(x)));

		IntStream.range(MainApp.NUMBER_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
			mutateSchedules.set(x, mutateSchedule(population.getSchedules().get(x)));
		});

		return mutatePopulation;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(MainApp.TOURNAMENT_SELECTION_SIZE, this.dataProvider);

		IntStream.range(0, MainApp.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
			tournamentPopulation.getSchedules().set(x,
					population.getSchedules().get((int) Math.random() * population.getSchedules().size()));
		});

		return tournamentPopulation;
	}

	private Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {
		Schedule crossoverSchedule = new Schedule(dataProvider).initialize();

		IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
			if (Math.random() > 0.5) {
				crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
			} else {
				crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
			}
		});

		return crossoverSchedule;
	}

	private Schedule mutateSchedule(Schedule schedule) {
		Schedule mutateSchedule = new Schedule(dataProvider).initialize();

		IntStream.range(0, schedule.getClasses().size()).forEach(x -> {
			if (MainApp.MUTATION_RATE > Math.random()) {
				schedule.getClasses().set(x, mutateSchedule.getClasses().get(x));
			}
		});

		return mutateSchedule;
	}
}
