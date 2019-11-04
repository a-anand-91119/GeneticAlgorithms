package com.anand.genetics.algorithm;

import java.util.Random;

public class GeneticAlgorithm {
	private static String expectedCharSequence;
	private double crossoverRate;
	private double mutationRate;
	private int numberOfEliteChromosomes;
	private int tournamentSelectionSize;
	private int populationSize;

	public GeneticAlgorithm(String expectedCharSequence, double crossoverRate, double mutationRate,
			int numberOfEliteChromosomes, int tournamentSelectionSize, int populationSize) {
		super();
		GeneticAlgorithm.expectedCharSequence = expectedCharSequence;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.numberOfEliteChromosomes = numberOfEliteChromosomes;
		this.tournamentSelectionSize = tournamentSelectionSize;
		this.populationSize = populationSize;
	}

	public GeneticAlgorithm initialize() {
		return this;
	}

	public static String getExpectedCharSequence() {
		return expectedCharSequence;
	}

	/**
	 * Method Evolves the current population and returns the next generation.
	 * 
	 * @param population the population to be evolved
	 * @return the next evolved generation
	 */
	public Population evolvePopulation(Population population) {
		return mutatePopulation(crossOverPopulation(population));
	}

	private Population crossOverPopulation(Population population) {
		Population crossOverpopulation = new Population(populationSize);

		for (int i = 0; i < numberOfEliteChromosomes; i++)
			crossOverpopulation.getChromosomes()[i] = population.getChromosomes()[i];

		for (int i = numberOfEliteChromosomes; i < populationSize; i++) {
			if(Math.random() < this.crossoverRate) {
				Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
				Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
				
				crossOverpopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
			}else {
				crossOverpopulation.getChromosomes()[i] = selectTournamentPopulation(population).getChromosomes()[0];
			}
		}

		return crossOverpopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(populationSize);

		for (int i = 0; i < numberOfEliteChromosomes; i++)
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];

		for (int i = numberOfEliteChromosomes; i < populationSize; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		
		return mutatePopulation;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(tournamentSelectionSize);
		for (int i = 0; i < tournamentSelectionSize; i++) {
			tournamentPopulation.getChromosomes()[i] = population
					.getChromosomes()[(int) (Math.random() * population.getPopulationSize())];
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}

	private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverChromosome = new Chromosome();
		for (int i = 0; i < crossoverChromosome.getTypedCharSequence().length; i++) {
			if (Math.random() > crossoverRate)
				crossoverChromosome.getTypedCharSequence()[i] = chromosome1.getTypedCharSequence()[i];
			else
				crossoverChromosome.getTypedCharSequence()[i] = chromosome2.getTypedCharSequence()[i];
		}
		return crossoverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Random random = new Random();
		Chromosome mutateChromosome = new Chromosome();
		for (int i = 0; i < mutateChromosome.getTypedCharSequence().length; i++) {
			if (Math.random() < mutationRate)
				mutateChromosome.getTypedCharSequence()[i] = Chromosome.AVAILABLE_CHARACTERS
						.charAt(random.nextInt(Chromosome.AVAILABLE_CHARACTERS.length()));
			else
				mutateChromosome.getTypedCharSequence()[i] = chromosome.getTypedCharSequence()[i];
		}
		return mutateChromosome;
	}
}
