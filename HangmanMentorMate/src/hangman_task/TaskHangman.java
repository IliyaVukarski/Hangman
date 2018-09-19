package hangman_task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TaskHangman {
	private static int currentScore = 0;
	public static void main(String[] args) {
		Map<String, List<String>> repository = new HashMap<>();
		String[] categories = {"Football_teams", "Books", "Programming_principles"};
		
		List<String> footballTeams = 
				Arrays.asList("Manchester United", "Arsenal", "Chelsea", "Liverpool", 
							  "FC Bayern Munchen", "Juventus","Real Madrid", 
							  "FC Barcelona", "Olympique de Marseille", "Paris Saint sGermain");
		repository.put(categories[0], footballTeams);
		
		List<String> books = 
			Arrays.asList("In Search of Lost Time", "Don Quixote", "Ulysses",
					"The Great Gatsby", "Moby Dick", "Hamlet" ,"War and Peace", "The Odyssey");
		repository.put(categories[1], books);
		
		List<String> prPrinciples = 
				Arrays.asList("Encapsulation", "Abstraction", "Inheritance", 
							  "Polymorphism", "KISS", "DRY", "Open Closed", 
							  "Single Responsibility", "Composition over inheritance");
		repository.put(categories[2], prPrinciples);
		
		Scanner scanner = new Scanner(System.in);
		String category = null;
		String randomWord = "";
		Random random = new Random();
		boolean gameIsRunning = true;
		
		while(gameIsRunning) {
			System.out.println("Please choose a category:");
			System.out.println("Football_teams");
			System.out.println("Books");
			System.out.println("Programming_principles");
			category = scanner.next();
			
			for(Map.Entry<String, List<String>> entry : repository.entrySet()) {
				String key = entry.getKey();
				if(key.equals(category)) {
					List<String> words = entry.getValue();
					int numberOfElements = random.nextInt(words.size());
					randomWord = words.get(numberOfElements);
				}
			}
			
			char[] randomWordToGuess = randomWord.toCharArray();
			int countOfGuesses = randomWordToGuess.length;
			char[] playerWordGuess = new char[countOfGuesses];
			
			for (int index = 0; index < playerWordGuess.length; index++) {
				if(randomWordToGuess[index] == ' ') {
					playerWordGuess[index] = ' ';
				}else{
					playerWordGuess[index] = '_';
				}
			}
			
			boolean worldIsGuessed = false;
			int totalAttempsleft = 10;
			int tries = 0;
			int remainingAttempts = totalAttempsleft;
			
			while(!worldIsGuessed) {
				System.out.println("Current guesses");
				printWord(playerWordGuess);
				remainingAttempts = totalAttempsleft + tries;
				System.out.printf("You have %d tries left. \n", remainingAttempts);
				System.out.println("Please enter a letter:");
				char input = scanner.next().charAt(0);
				
				for (int index = 0; index < playerWordGuess.length; index++) {
					if(randomWordToGuess[index] == input) {
						playerWordGuess[index] = input;
					}
				}
				if(!new String(randomWordToGuess).contains(String.valueOf(input))) {
					tries--;
				}
				
				if(remainingAttempts <= 0) {
					break;
				}
				if(theWordIsGuessed(playerWordGuess)) {
					worldIsGuessed = true;
					System.out.println("Congratulations you have revealed the word/phrase:");
					for (int index = 0; index < randomWordToGuess.length; index++) {
						System.out.print(randomWordToGuess[index] + " ");
					}
					currentScore++;
					System.out.println("/n Current score: " + currentScore);
				}
			}	
			
			if(!worldIsGuessed) {
				System.out.println("You ran out of guesses!");
			}
		}
		
		System.out.println("Game over");
	} 
	
	public static void printWord(char[] array) {
		for (int index = 0; index < array.length; index++) {
			System.out.print(array[index]);
		}
		System.out.println();
	}
	
	public static boolean theWordIsGuessed(char[] array) {
		for (int index = 0; index < array.length; index++) {
			if(array[index] == '_') {
				return false;
			}
		}
		return true;
	}
}