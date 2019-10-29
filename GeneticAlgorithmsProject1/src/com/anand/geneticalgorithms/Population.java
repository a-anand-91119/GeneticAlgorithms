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

	public Population(int length) {
		chromosomes = new Chromosome[length];
	}

	public Population initializePopulation() {
		for (int i = 0; i < this.chromosomes.length; i++) {
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosomes();
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
