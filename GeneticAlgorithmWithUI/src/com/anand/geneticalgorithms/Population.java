package com.anand.geneticalgorithms;

import java.util.Arrays;

import com.anand.geneticalgorithms.algorithm.GeneticAlgorithm;

/**
 * 
 * @author A Anand
 *
 */
public class Population {

	private Chromosome[] chromosomes;
	private GeneticAlgorithm geneticAlgorithm;

	public Population(int populationSize, GeneticAlgorithm geneticAlgorithm) {
		this.chromosomes = new Chromosome[populationSize];
		this.geneticAlgorithm = geneticAlgorithm;
	}

	public Population initializePopulation() {
		for (int i = 0; i < this.chromosomes.length; i++) {
			chromosomes[i] = new Chromosome(this.geneticAlgorithm).initializeChromosomes();
		}
		sortChromosomesByFitness();
		return this;
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(this.chromosomes, (chromosome1, chromosome2) -> {
			int compareValue = 0;
			if (chromosome1.getFitness() > chromosome2.getFitness())
				compareValue = -1;
			else if (chromosome1.getFitness() < chromosome2.getFitness())
				compareValue = 1;
			return compareValue;
		});
	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}

	@Override
	public String toString() {
		return "Population [chromosomes=" + Arrays.toString(chromosomes) + "]";
	}

}
