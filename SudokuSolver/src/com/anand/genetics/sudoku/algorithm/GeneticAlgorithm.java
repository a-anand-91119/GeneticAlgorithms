package com.anand.genetics.sudoku.algorithm;

import java.util.Random;

import com.anand.genetics.sudoku.Chromosome;
import com.anand.genetics.sudoku.Population;

public class GeneticAlgorithm {

	private int populationSize;
	private int tournamentPopulationSize;
	private int noOfEliteChromosomes;
	private double crossoverRate;
	private double mutationRate;
	private static final Random RANDOM = new Random();

	public GeneticAlgorithm(int populationSize, int tournamentPopulationSize, double crossoverRate, double mutationRate,
			int noOfEliteChromosomes) {
		super();
		this.populationSize = populationSize;
		this.tournamentPopulationSize = tournamentPopulationSize;
		this.noOfEliteChromosomes = noOfEliteChromosomes;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
	}

	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(this.populationSize);

		for (int i = 0; i < noOfEliteChromosomes; i++) {
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = noOfEliteChromosomes; i < populationSize; i++) {
			if (Math.random() < this.crossoverRate) {
				Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
				Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
				crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
			} else {
				crossoverPopulation.getChromosomes()[i] = selectTournamentPopulation(population).getChromosomes()[0];
			}
		}

		return crossoverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(this.populationSize);

		for (int i = 0; i < noOfEliteChromosomes; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = noOfEliteChromosomes; i < populationSize; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		return mutatePopulation;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(this.tournamentPopulationSize);
		for (int i = 0; i < this.tournamentPopulationSize; i++) {
			tournamentPopulation.getChromosomes()[i] = population
					.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
		}
		tournamentPopulation.sortPopulationByFitness();
		return tournamentPopulation;
	}

	private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverChromosome = new Chromosome();
		for (int i = 0; i < chromosome1.getGene().length; i++) {
			for (int j = 0; j < chromosome1.getGene()[i].length; j++) {
				if (Math.random() > this.crossoverRate) {
					crossoverChromosome.setGene(i, j, chromosome1.getGene()[i][j]);
				} else {
					crossoverChromosome.setGene(i, j, chromosome2.getGene()[i][j]);
				}
			}
		}
		return crossoverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome();
		for (int i = 0; i < chromosome.getGene().length; i++) {
			for (int j = 0; j < chromosome.getGene()[i].length; j++) {
				if (Math.random() < this.mutationRate) {
					mutateChromosome.setGene(i, j, RANDOM.nextInt(9) + 1);
				} else {
					mutateChromosome.setGene(i, j, chromosome.getGene()[i][j]);
				}
			}
		}
		return mutateChromosome;
	}
}
