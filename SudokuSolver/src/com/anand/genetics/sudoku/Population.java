package com.anand.genetics.sudoku;

import java.util.Arrays;

public class Population {

	private Chromosome[] chromosomes;

	public Population(int populationSize) {
		chromosomes = new Chromosome[populationSize];
	}

	public Population initialize() {
		for (int i = 0; i < chromosomes.length; i++)
			chromosomes[i] = new Chromosome().initialize();
		sortPopulationByFitness();
		return this;
	}

	public void sortPopulationByFitness() {
		Arrays.sort(chromosomes, (chromosome1, chromosome2) -> {
			if (chromosome1.getFitness() > chromosome2.getFitness())
				return -1;
			else if (chromosome1.getFitness() < chromosome2.getFitness())
				return 1;
			return 0;
		});
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}

}
