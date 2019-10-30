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

	private int[] genes;
	// Fitness shows how close a chromosome is to the TARGET_CHROMOSOME
	private Integer fitness;
	private Boolean isFitnessChanged = true;
	private GeneticAlgorithm geneticAlgorithm;

	public Chromosome(GeneticAlgorithm geneticAlgorithm) {
		genes = new int[geneticAlgorithm.getTargetChromosomeGenes().length];
		this.geneticAlgorithm = geneticAlgorithm;
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
			if (genes[i] == geneticAlgorithm.getTargetChromosomeGenes()[i])
				chromosomeFitness++;
		}
		return chromosomeFitness;
	}

	public Integer getFitness() {
		if (isFitnessChanged) {
			isFitnessChanged = false;
			fitness = recalculateFitness();
		}
		return fitness;
	}

	public void setFitness(Integer fitness) {
		this.fitness = fitness;
	}

	public int[] getGenes() {
		isFitnessChanged = true;
		return genes;
	}

	public void setGenes(int[] genes) {
		this.genes = genes;
	}

	@Override
	public String toString() {
		return "Chromosome [genes=" + Arrays.toString(genes) + "]";
	}

}
