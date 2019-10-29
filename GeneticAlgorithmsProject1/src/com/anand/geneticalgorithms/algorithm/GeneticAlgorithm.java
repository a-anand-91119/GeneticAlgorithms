package com.anand.geneticalgorithms.algorithm;

import com.anand.geneticalgorithms.Chromosome;
import com.anand.geneticalgorithms.Population;

/**
 * 
 * @author A Anand
 *
 */
public class GeneticAlgorithm {

	public static final Integer[] TARGET_CHROMOSOME = { 
			1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0,
			1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1,
			1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0,
			1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0,
			1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1};
	public static final Integer POPULATION_SIZE = 100;
	public static final Integer NUMBER_OF_ELITE_CHROMOSOMES = 20;
	public static final Integer TOURNAMENT_SELECTION_SIZE = 80;
	public static final Double MUTATION_RATE = 0.1;

	private Population crossOverPopulation(Population population) {
		Population crossOverPopulation = new Population(population.getChromosomes().length);

		for (int i = 0; i < NUMBER_OF_ELITE_CHROMOSOMES; i++) {
			crossOverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = NUMBER_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++) {
			Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
			Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];

			crossOverPopulation.getChromosomes()[i] = crossOverChromosome(chromosome1, chromosome2);
		}
		return crossOverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getChromosomes().length);

		for (int i = 0; i < NUMBER_OF_ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		
		for (int i = NUMBER_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		
		return mutatePopulation;
	}

	public Population evolvePopulation(Population population) {
		return mutatePopulation(crossOverPopulation(population));
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
		for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPopulation.getChromosomes()[i] = population
					.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}

	private Chromosome crossOverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossOverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0; i < chromosome1.getGenes().length; i++) {
			if (Math.random() < 0.5)
				crossOverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
			else
				crossOverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
		}
		return crossOverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0; i < chromosome.getGenes().length; i++) {
			if (Math.random() < MUTATION_RATE) {
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
}
