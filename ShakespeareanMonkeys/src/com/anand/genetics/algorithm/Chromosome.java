package com.anand.genetics.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Each chromosome denotes a single solution to the problem, which may or may
 * not be correct
 * 
 * @author A Anand
 *
 */
public class Chromosome {

	private char[] typedCharSequence;
	private double fitness;
	private boolean isFitnessChaged = true;
	public static final String AVAILABLE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz `,.-!'\";";

	public Chromosome() {
		super();
		typedCharSequence = new char[GeneticAlgorithm.getExpectedCharSequence().length()];
	}

	public Chromosome initialize() {
		Random random = new Random();
		int size = AVAILABLE_CHARACTERS.length();
		for (int i = 0; i < GeneticAlgorithm.getExpectedCharSequence().length(); i++) {
			typedCharSequence[i] = AVAILABLE_CHARACTERS.charAt(random.nextInt(size));
		}
		return this;
	}

	private double recalculateFitness() {
		double newFitness = 0;
		for (int i = 0; i < GeneticAlgorithm.getExpectedCharSequence().length(); i++) {
			if (typedCharSequence[i] == GeneticAlgorithm.getExpectedCharSequence().charAt(i))
				newFitness++;
		}
		newFitness /= GeneticAlgorithm.getExpectedCharSequence().length();
		return newFitness;
	}
	
	private double optimizedFitness() {
		double newFitness = recalculateFitness();
		return Math.pow(newFitness, 4);
	}

	public char[] getTypedCharSequence() {
		isFitnessChaged = true;
		return typedCharSequence;
	}

	public void setTypedCharSequence(char[] typedCharSequence) {
		this.typedCharSequence = typedCharSequence;
	}

	public double getFitness() {
		if (isFitnessChaged) {
			isFitnessChaged = false;
			fitness = optimizedFitness();
		}
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public boolean isFitnessChaged() {
		return isFitnessChaged;
	}

	public void setFitnessChaged(boolean isFitnessChaged) {
		this.isFitnessChaged = isFitnessChaged;
	}

	@Override
	public String toString() {
		return "Chromosome [typedString=" + Arrays.toString(typedCharSequence).replace(", ", "") + ", fitness=" + fitness + "]";
	}

}
