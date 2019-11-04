package com.anand.genetics.algorithm;

import java.util.Arrays;

/**
 * Collection of Chromosomes makes up a generation of population
 * 
 * @author A Anand
 *
 */
public class Population {

	private Chromosome[] chromosomes;
	private int populationSize;

	public Population(int populationSize) {
		super();
		this.populationSize = populationSize;
		chromosomes = new Chromosome[populationSize];
	}

	public Population initialize() {
		for (int i = 0; i < populationSize; i++) {
			chromosomes[i] = new Chromosome().initialize();
		}
		sortChromosomesByFitness();
		
		return this;
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(this.chromosomes, (chromosome1, chromosome2) -> {
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

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

}
