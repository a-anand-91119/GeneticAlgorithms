package com.anand.geneticalgorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 
 * @author A Anand
 *
 */
public class Population {
	private List<Schedule> schedules;

	public Population(int size, DataProvider dataProvider) {
		schedules = new ArrayList<Schedule>(size);
		IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(dataProvider).initialize()));
	}

	public Population sortPopulationByFitness() {
		schedules.sort((s1, s2) -> {
			if (s1.getFitness() > s2.getFitness())
				return -1;
			if (s1.getFitness() < s2.getFitness())
				return 1;
			return 0;
		});
		return this;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}
