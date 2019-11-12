package com.anand.genetics.sudoku;

import java.util.Random;

/**
 * 
 * @author A Anand
 *
 */
public class Chromosome {

	private int[][] gene = new int[9][9];
	private double fitness;
	private boolean isFitnessChanged = true;
	private static Random random = new Random();

	public static void main(String[] args) {
		Chromosome chromosome = new Chromosome();
		chromosome.initialize();
		chromosome.getGene();
		System.out.println(chromosome.getFitness());
		System.out.println(chromosome + "");
	}

	public Chromosome initialize() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				gene[i][j] = random.nextInt(9) + 1;
		return this;
	}

	
	private double recalculateFitness() {
		double score = 0;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				score += getScore(gene[x][y], x, y);
			} 
		} 
		return score / (double)243;
	}

	private double getScore(int value, int row, int column) {
		double score = 3;
		for (int i = 0; i < 9; i++) {
			if(value == gene[row][i] && i != column) {
				score--;
				break;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if(value == gene[i][column] && i != row) {
				score--;
				break;
			}
		}
			
		int startColumn = 3 * (column / 3);
		int startRow = 3 * (row / 3);
		for(int i = startRow; i < startRow + 3; i++) {
			for(int j = startColumn; j < startColumn + 3; j++) {
				if (gene[i / 3][i % 3] == gene[j / 3][j % 3] && ((i / 3 != j / 3) && (i % 3 != j % 3))) {
					score--;
					break;
				}
			}
		}
		
		return score;
	}

	public int[][] getGene() {
		isFitnessChanged = true;
		return gene;
	}

	public void setGene(int i, int j, int value) {
		gene[i][j] = value;
	}

	public void setGene(int[][] gene) {
		this.gene = gene;
	}

	public double getFitness() {
		if (isFitnessChanged) {
			fitness = recalculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public boolean isFitnessChanged() {
		return isFitnessChanged;
	}

	public void setFitnessChanged(boolean isFitnessChanged) {
		this.isFitnessChanged = isFitnessChanged;
	}

	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder("Chromosome [fitness=" + fitness + "]\n");
		returnString.append("-------------------------\n");
		// returnString = new StringBuilder();
		for (int i = 0; i < gene.length; i++) {
			for (int j = 0; j < gene.length; j++) {
				if (j % 3 == 0) {
					returnString.append("|").append(" ");
				}
				returnString.append(gene[i][j]).append(" ");
			}
			returnString.append("|\n");
			if (i != gene.length - 1 && (i + 1) % 3 == 0)
				returnString.append("|-----------------------|\n");
		}
		returnString.append("-------------------------");
		/*
		 * for (int i = 0; i < gene.length; i++)
		 * returnString.append(Arrays.toString(gene[i]));
		 */
		return returnString.toString();
	}

}
