package com.anand.geneticalgorithms.driver;

import java.util.Arrays;

import com.anand.geneticalgorithms.Population;
import com.anand.geneticalgorithms.algorithm.GeneticAlgorithm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Driver Class To Perform Genetic Algorithm
 * 
 * @author A Anand
 *
 */
public class DriverClass extends Application {

	private TextArea processTA = null;
	private TextField targetSolutionTF = null;
	private TextField populationSizeTF = null;
	private TextField mutationRateTF = null;
	private TextField crossoverRateTF = null;
	private TextField numberOfEliteChromosomeTF = null;
	private TextField tournamentSelectionTF = null;
	private Button startButton = null;

	private GeneticAlgorithm geneticAlgorithm = null;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initializeUIComponents(primaryStage);

		startButton.setOnAction(e -> {
			geneticAlgorithm = new GeneticAlgorithm(getGenesArray(targetSolutionTF.getText()),
					new Integer(populationSizeTF.getText()), new Integer(numberOfEliteChromosomeTF.getText()),
					new Integer(tournamentSelectionTF.getText()), new Double(mutationRateTF.getText()),
					new Double(crossoverRateTF.getText()));

			Platform.runLater(() -> {
				processTA.clear();
				Population population = new Population(geneticAlgorithm.getPopulationSize(), geneticAlgorithm)
						.initializePopulation();

				line();

				processTA.appendText(
						"Generation #0 | Fittest Chromosome Fitness: " + population.getChromosomes()[0].getFitness() + "\n");
				showPopulation(population,
						"Target Chromosome: " + Arrays.toString(geneticAlgorithm.getTargetChromosomeGenes()));

				int generationNumber = 0;

				while (population.getChromosomes()[0]
						.getFitness() < geneticAlgorithm.getTargetChromosomeGenes().length) {
					generationNumber++;
					line();
					population = geneticAlgorithm.evolvePopulation(population);
					population.sortChromosomesByFitness();
					processTA.appendText("Generation #" + generationNumber + " | Fittest Chromosome Fitness: "
							+ population.getChromosomes()[0].getFitness() + "\n");
					showPopulation(population,
							"Target Chromosome: " + Arrays.toString(geneticAlgorithm.getTargetChromosomeGenes()));

				}
			});
		});

		primaryStage.show();
	}

	public void initializeUIComponents(Stage mainStage) {
		mainStage.setTitle("Genetic Algorithm Implementation");

		processTA = new TextArea();
		processTA.setMinHeight(500);
		processTA.setEditable(false);

		targetSolutionTF = new TextField();
		targetSolutionTF.setPromptText("Target Solution...");
		populationSizeTF = new TextField();
		populationSizeTF.setPromptText("Population Size...");
		mutationRateTF = new TextField();
		mutationRateTF.setPromptText("MutationRate...");
		crossoverRateTF = new TextField();
		crossoverRateTF.setPromptText("Crossover Rate...");
		numberOfEliteChromosomeTF = new TextField();
		numberOfEliteChromosomeTF.setPromptText("Number Of Elite Chromosomes...");
		tournamentSelectionTF = new TextField();
		tournamentSelectionTF.setPromptText("Tournament Selection Size...");
		startButton = new Button("Start");

		HBox hBox = new HBox();
		hBox.getChildren().addAll(targetSolutionTF, populationSizeTF, mutationRateTF, crossoverRateTF,
				numberOfEliteChromosomeTF, tournamentSelectionTF, startButton);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(processTA, hBox);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(vBox);

		mainStage.setScene(new Scene(stackPane, 1000, 530));

	}

	private int[] getGenesArray(String genesText) {
		return Arrays.stream(genesText.split(",")).map(x -> x.trim()).mapToInt(x -> Integer.parseInt(x)).toArray();
	}

	public void showPopulation(Population population, String heading) {
		processTA.appendText(heading + "\n");
		line();
		for (int i = 0; i < population.getChromosomes().length; i++) {
			processTA.appendText("Chromosome #" + i + " | " + Arrays.toString(population.getChromosomes()[i].getGenes())
					+ " | Fitness: " + population.getChromosomes()[i].getFitness() + "\n");
		}
		processTA.appendText("\\n");
	}

	public void line() {
		processTA.appendText(
				"--------------------------------------------------------------------------------------------\n");
	}

}
