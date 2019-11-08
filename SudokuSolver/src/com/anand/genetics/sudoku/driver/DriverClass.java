package com.anand.genetics.sudoku.driver;

import com.anand.genetics.sudoku.Population;
import com.anand.genetics.sudoku.algorithm.GeneticAlgorithm;

public class DriverClass {

	public static void main(String[] args) {

		int populationSize = 1000;
		int tournamentPopulationSize = 500;
		int noOfEliteChromosomes = 100;
		double crossoverRate = 0.5;
		double mutationRate = 0.01;

		Population population = new Population(populationSize).initialize();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(populationSize, tournamentPopulationSize,
				crossoverRate, mutationRate, noOfEliteChromosomes);

		int generation = 0;
		displayPopulation(generation, population, populationSize);

		while (population.getChromosomes()[0].getFitness() < (double) 1) {
			generation++;
			population = geneticAlgorithm.evolve(population);
			population.sortPopulationByFitness();
			displayPopulation(generation, population, populationSize);
		}
	}

	private static void displayPopulation(int generation, Population population, int populationSize) {
		System.out.println("Generation ::" + generation);
		//System.out.println(population.getChromosomes()[0].getFitness());
		System.out.println(population.getChromosomes()[0]);
		/*for(int i=0;i<populationSize;i++) {
			System.out.println(population.getChromosomes()[i]);
		}*/
	}
}
