import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** This program implements the Wordle game that has been so popular on social media. The game chooses a word from a file of possible words.
 * It then gives the user 6 turns to guess the word. For each guess it shows which letters are in the word but not in the right place with an !,
 * in the word and in the right place with an *, and not in the word with an X. This allows the user to guess the correct word.
 * 
 * One of the things that makes this game both fun and frustrating is that they only publish on word a day. Using this program, you can
 * practice and play multiple times.
 * 
 * The data file was taken from: https://medium.com/@owenyin/here-lies-wordle-2021-2027-full-answer-list-52017ee99e86
 * Wordle is owned by the New York Times.
 */
public class Project6 {

	// Constants for the game setup
	final private static int NUMBER_WORDS = 255;
	final private static int MAX_GUESSES = 6;
	final private static int WORD_LENGTH = 5;
	final private static String FILENAME = "Wordle.txt";
	
	// Symbols for printing out results
	final private static char SUCCESS = '*';
	final private static char RIGHT_LETTER_WRONG_PLACE = '!';
	final private static char WRONG_LETTER = 'X';
	
	/** Play a version of the Wordle game. The data is stored in Wodle.txt in a strange format. Users will choose the number of a hidden
	 * word. This allows users to play the game repeatedly. Users then guess five letter words that could be the chosen word. They will e
	 * told which letters and positions are correct in the word. Users get only six guesses. If they don't guess it in six guesses, they
	 * lose the game.
	 * @param args There are no command line arguments
	 * @throws FileNotFoundException If the Wordle.txt file is not available in the Project directory (not the src directory).
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		// Declare and construct a Scanner that is attached to the keyboard.
		Scanner input = new Scanner(System.in);
		int i;
		// Get the hidden word from the file by calling the getWordFromFileMethod
		String word = getWordFromFile(WORD_LENGTH, input);
		// Convert the word to lower case.
		word = word.toLowerCase();
		int output = 0;
		int guessCounter = 0;
		// Let the user make MAX_GUESSES. Stop if they get all the characters correct
		for (i = 0; i < MAX_GUESSES && output != WORD_LENGTH; i++)
		{
			System.out.println("Enter your guess. Remember the word has " + WORD_LENGTH + " letters");
			String guess = input.next();
			guess = guess.toLowerCase();
			output = showResult(word, guess);
			guessCounter++;
		
		}
		if (output != WORD_LENGTH)
		{
			System.out.println("The word was " + word);
		}
		System.out.println(guessCounter + " / " + MAX_GUESSES);
		
			

	}
	
	/** Choose a word from the Wordle file. The word will be chosen by letting the user pick a
	 * word number. This allows the user to play the game multiple times in the same day.
	 * @param size The number of words that are in the file.
	 * @param keyboard A Scanner that is attached to the console--this should be constructed in the 
	 * main program.
	 * @return The selected word.
	 * @throws FileNotFoundException If the given filename is not in the home folder for the project.
	 */
	public static String getWordFromFile(int size, Scanner keyboard) throws FileNotFoundException
	{
		// Open the file
		Scanner file = new Scanner (new File(FILENAME));
		

		System.out.println("Which word number would you like? The maximum is " + NUMBER_WORDS);
		int chosenWordNum = keyboard.nextInt();
		int i;

		for (i = 0; i < chosenWordNum; i++)
		{
			file.nextLine();
		}
        for (i = 0; i < 5; i++)
        {
        	file.next();
        }
		String wordRead = file.next();
		// No need to read the rest of the file
		
	
		file.close();
		

		return wordRead;
	}
	
	/** Show the user how well their guess did by printing an * if the letter is the right
	 * letter in the correct position, an ! if the letter is in the word but not in the correct
	 * position, and an X otherwise. Output should go to the console and terminate with a newline.
	 * 
	 * @param chosenWord The hidden word in the game.
	 * @param guess The user's guess.
	 * @return The number of characters that are in the correct position in the guess.
	 */
	public static int showResult(String chosenWord, String guess)
	{
		
		int i;
		char [] result = new char[WORD_LENGTH];
		int resultNum = 0;
		
		for (i = 0; i < WORD_LENGTH; i++)
		{
			if (guess.charAt(i) == chosenWord.charAt(i))
			{
				result[i] = SUCCESS;
				resultNum ++;
				
			}
			else if (chosenWord.contains("" + guess.charAt(i)))
			{
				result[i] = RIGHT_LETTER_WRONG_PLACE;
			}
			else if (guess.charAt(i) != chosenWord.charAt(i))
			{
				result[i] = WRONG_LETTER;
			}
			
		}		
		System.out.println(result);
		return resultNum;
	}
	
	
}
