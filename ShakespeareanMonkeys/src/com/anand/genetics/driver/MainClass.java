package com.anand.genetics.driver;

import com.anand.genetics.algorithm.GeneticAlgorithm;
import com.anand.genetics.algorithm.Population;

public class MainClass {

	public static void main(String[] args) {

		MainClass mainClass = new MainClass();

		String expectedCharSequence = "To be or not to be, that is the question; Whether 'tis nobler in the mind to suffer. - William Shakespeare";
		int initialPopulationSize = 200;
		double crossoverRate = 0.5;
		double mutationRate = 0.01;
		int numberOfEliteChromosomes = 20;
		int tournamentSelectionSize = 50;
		int generation = 0;

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(expectedCharSequence, crossoverRate, mutationRate,
				numberOfEliteChromosomes, tournamentSelectionSize, initialPopulationSize);
		Population population = new Population(initialPopulationSize).initialize();
		mainClass.displayPopulation(population, generation);

		while (population.getChromosomes()[0].getFitness() < 1) {
			generation++;
			population = geneticAlgorithm.evolvePopulation(population);
			population.sortChromosomesByFitness();
			mainClass.displayPopulation(population, generation);
		}
	}

	private void displayPopulation(Population population, int generation) {
		System.out.println("\nCurrent Generation: " + generation);
		System.out.println("Fittest Chromsome: " + population.getChromosomes()[0]);
		System.out.println("--------------------------------------------------------------");
		for (int i = 0; i < population.getPopulationSize(); i++) {
			System.out.println(population.getChromosomes()[i]);
		}
		System.out.println("--------------------------------------------------------------");
	}

}
