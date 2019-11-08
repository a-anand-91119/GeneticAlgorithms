package com.anand.genetics.sudoku;

import java.util.Arrays;
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

	public static void main(String[] args) {
		Chromosome chromosome = new Chromosome();
		chromosome.initialize();
		chromosome.getGene();
		System.out.println(chromosome.getFitness());
		System.out.println(chromosome + "");
	}

	public Chromosome initialize() {
		Random random = new Random();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				gene[i][j] = random.nextInt(9) + 1;
		return this;
	}

	private double recalculateFitness() {
		double duplicates = 0;
		for (int i = 0; i < 9; i++)
			duplicates += checkRow(gene, i, 9);
		for (int i = 0; i < 9; i++)
			duplicates += checkColumn(gene, 9, i);
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if(gene[i/3][i%3] == gene[j/3][j%3])
					duplicates++;
		return 1 - (duplicates / (double) 81);
	}

	private double checkRow(int[][] board, int row, int column) {
		double rowDuplicateCount = 0;
		for (int i = 0; i < column; i++) {
			for (int j = i + 1; j < column; j++) {
				if (board[row][i] == board[row][j]) {
					rowDuplicateCount++;
				}
			}
		}
		return rowDuplicateCount;
	}

	private double checkColumn(int[][] board, int row, int column) {
		double rowDuplicateCount = 0;
		for (int i = 0; i < row; i++) {
			for (int j = i + 1; j < row; j++) {
				if (board[i][column] == board[j][column]) {
					rowDuplicateCount++;
				}
			}
		}
		return rowDuplicateCount;
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
		//returnString = new StringBuilder();
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
		/*for (int i = 0; i < gene.length; i++)
			returnString.append(Arrays.toString(gene[i]));*/
		return returnString.toString();
	}

}
