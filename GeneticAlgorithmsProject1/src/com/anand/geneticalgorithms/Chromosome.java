package com.anand.geneticalgorithms;

import java.util.Arrays;

import com.anand.geneticalgorithms.algorithm.GeneticAlgorithm;

/**
 * Chromosome Class.
 * 
 * @author A Anand
 *
 */
public class Chromosome {

	private Integer[] genes;
	// Fitness shows how close a chromosome is to the TARGET_CHROMOSOME
	private Integer fitness;
	private Boolean isFitnessChanged = true;

	public Chromosome(int length) {
		genes = new Integer[length];
	}

	public Chromosome initializeChromosomes() {
		for (int i = 0; i < this.genes.length; i++) {
			if (Math.random() >= 0.5)
				this.genes[i] = 1;
			else
				this.genes[i] = 0;
		}
		return this;
	}

	public Integer recalculateFitness() {
		Integer chromosomeFitness = 0;
		for (int i = 0; i < this.genes.length; i++) {
			if (genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i])
				chromosomeFitness++;
		}
		return chromosomeFitness;
	}

	public Integer getFitness() {
		if(isFitnessChanged) {
			isFitnessChanged = false;
			fitness = recalculateFitness();
		}
		return fitness;
	}

	public void setFitness(Integer fitness) {
		this.fitness = fitness;
	}

	public Integer[] getGenes() {
		isFitnessChanged = true;
		return genes;
	}

	public void setGenes(Integer[] genes) {
		this.genes = genes;
	}

	@Override
	public String toString() {
		return "Chromosome [genes=" + Arrays.toString(genes) + "]";
	}

}
