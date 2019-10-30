package com.anand.geneticalgorithms.algorithm;

import com.anand.geneticalgorithms.Chromosome;
import com.anand.geneticalgorithms.Population;

/**
 * 
 * @author A Anand
 *
 */
public class GeneticAlgorithm {

	private int[] targetSolution;
	private int populationSize;
	private int noOfEliteChoromosomes;
	private int tournamentSelectionSize;
	private double mutationRate;
	private double crossoverRate;

	public GeneticAlgorithm(int[] targetSolution, int populationSize, int noOfEliteChoromosomes,
			int tournamentSelectionSize, double mutationRate, double crossoverRate) {
		super();
		this.targetSolution = targetSolution;
		this.populationSize = populationSize;
		this.noOfEliteChoromosomes = noOfEliteChoromosomes;
		this.tournamentSelectionSize = tournamentSelectionSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}

	private Population crossOverPopulation(Population population) {
		Population crossOverPopulation = new Population(this.populationSize, this);

		for (int i = 0; i < this.noOfEliteChoromosomes; i++) {
			crossOverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		Chromosome chromosome1 = null;
		Chromosome chromosome2 = null;
		for (int i = this.noOfEliteChoromosomes; i < population.getChromosomes().length; i++) {
			if(Math.random() <= this.crossoverRate) {
				chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
				chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
	
				crossOverPopulation.getChromosomes()[i] = crossOverChromosome(chromosome1, chromosome2);
			}else {
				crossOverPopulation.getChromosomes()[i] = selectTournamentPopulation(population).getChromosomes()[0];
			}
		}
		return crossOverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(this.populationSize, this);

		for (int i = 0; i < this.noOfEliteChoromosomes; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = this.noOfEliteChoromosomes; i < population.getChromosomes().length; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}

		return mutatePopulation;
	}

	public Population evolvePopulation(Population population) {
		return mutatePopulation(crossOverPopulation(population));
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(this.tournamentSelectionSize, this);
		for (int i = 0; i < this.tournamentSelectionSize; i++) {
			tournamentPopulation.getChromosomes()[i] = population
					.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}

	private Chromosome crossOverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossOverChromosome = new Chromosome(this);
		for (int i = 0; i < chromosome1.getGenes().length; i++) {
			if (Math.random() < 0.5)
				crossOverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
			else
				crossOverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
		}
		return crossOverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(this);
		for (int i = 0; i < chromosome.getGenes().length; i++) {
			if (Math.random() < this.mutationRate) {
				if (Math.random() < 0.5)
					mutateChromosome.getGenes()[i] = 1;
				else
					mutateChromosome.getGenes()[i] = 0;
			} else {
				mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
			}
		}
		return mutateChromosome;
	}

	public int[] getTargetChromosomeGenes() {
		return targetSolution;
	}

	public void setTargetSolution(int[] targetSolution) {
		this.targetSolution = targetSolution;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getNoOfEliteChoromosomes() {
		return noOfEliteChoromosomes;
	}

	public void setNoOfEliteChoromosomes(int noOfEliteChoromosomes) {
		this.noOfEliteChoromosomes = noOfEliteChoromosomes;
	}

	public int getTournamentSelectionSize() {
		return tournamentSelectionSize;
	}

	public void setTournamentSelectionSize(int tournamentSelectionSize) {
		this.tournamentSelectionSize = tournamentSelectionSize;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public double getCrossoverRate() {
		return crossoverRate;
	}

	public void setCrossoverRate(double crossoverRate) {
		this.crossoverRate = crossoverRate;
	}

}
