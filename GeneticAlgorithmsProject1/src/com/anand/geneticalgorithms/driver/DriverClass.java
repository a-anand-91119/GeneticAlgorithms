package com.anand.geneticalgorithms.driver;

import java.util.Arrays;

import com.anand.geneticalgorithms.Population;
import com.anand.geneticalgorithms.algorithm.GeneticAlgorithm;

/**
 * Driver Class To Perform Genetic Algorithm
 * 
 * @author A Anand
 *
 */
public class DriverClass {

	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		
		line();
		System.out.println("Generation #0 | Fittest Chromosome Fitness: " + population.getChromosomes()[0].getFitness());
		showPopulation(population, "Target Chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
	
		int generationNumber = 0;
		
		while(population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
			generationNumber++;
			line();
			population = geneticAlgorithm.evolvePopulation(population);
			population.sortChromosomesByFitness();
			System.out.println("Generation #" + generationNumber + " | Fittest Chromosome Fitness: " + population.getChromosomes()[0].getFitness());
			showPopulation(population, "Target Chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
			
		}
	}

	public static void showPopulation(Population population, String heading) {
		System.out.println(heading);
		line();
		for (int i = 0; i < population.getChromosomes().length; i++) {
			System.out.println("Chromosome #" + i + " | " + Arrays.toString(population.getChromosomes()[i].getGenes())
					+ " | Fitness: " + population.getChromosomes()[i].getFitness());
		}
		System.out.println();
	}
	
	public static void line() {
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}
