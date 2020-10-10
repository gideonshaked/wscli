
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;

public class wscli {
	private static ArrayList<String> usedWords = new ArrayList<String>();
	private static ArrayList<int[]> intersectionCoords = new ArrayList<int[]>();

	public static void main(String[] args) {
		String wordsPath = parseArgs(args);

		char[][] grid = new char[20][20];
		int[] orientations = getOrientations();

		ArrayList<String> freeWords = getWordList(wordsPath);
		placeVerticals(orientations[0], grid, freeWords);
		placeHorizontals(orientations[1], grid, freeWords);
		placeDiagonals(orientations[2], grid, freeWords);

		fillSpaces(grid);
		printGrid(grid);
	}

	public static void placeVerticals(int howMany, char[][] grid, ArrayList<String> freeWords) {
		for (int placed = 1; placed <= howMany; placed++) {
			String word = getWord(freeWords);
			int row;
			int col;
			boolean isInsertable = false;

			outer: do {
				// create random row and column
				row = getRandom(0, grid.length - word.length());
				col = getRandom(0, grid[0].length);

				// check if word can fit
				for (int i = 0; i < word.length(); i++) {
					if (!(grid[row + i][col] == 0 || grid[row + i][col] == word.charAt(i)))
						continue outer;
				}
				isInsertable = true;
			} while (!isInsertable);

			// insert word
			for (int i = 0; i < word.length(); i++) {
				if (grid[row + i][col] == word.charAt(i)) {
					intersectionCoords.add(new int[] { row, col + i });
					grid[row + i][col] = Character.toUpperCase(word.charAt(i));
				} else {
					grid[row + i][col] = word.charAt(i);
				}
			}
		}
	}

	public static void placeHorizontals(int howMany, char[][] grid, ArrayList<String> freeWords) {
		for (int placed = 1; placed <= howMany; placed++) {
			String word = getWord(freeWords);
			int row;
			int col;
			boolean isInsertable = false;

			outer: do {
				// create random row and column
				row = getRandom(0, grid.length);
				col = getRandom(0, grid[0].length - word.length());

				// check if word can fit
				for (int i = 0; i < word.length(); i++) {
					if (!(grid[row][col + i] == 0 || grid[row][col + i] == word.charAt(i)))
						continue outer;
				}
				isInsertable = true;
			} while (!isInsertable);

			// insert word
			for (int i = 0; i < word.length(); i++) {
				if (grid[row][col + i] == word.charAt(i)) {
					intersectionCoords.add(new int[] { row + i, col });
					grid[row][col + i] = Character.toUpperCase(word.charAt(i));
				} else {
					grid[row][col + i] = word.charAt(i);
				}
			}
		}
	}

	public static void placeDiagonals(int howMany, char[][] grid, ArrayList<String> freeWords) {
		for (int placed = 1; placed <= howMany / 2; placed++) {
			String word = getWord(freeWords);
			int row;
			int col;
			boolean isInsertable = false;

			outer: do {
				// create random row and column
				row = getRandom(0, grid.length);
				col = getRandom(0, grid[0].length);

				// check if the word can fit
				for (int i = 0; i < word.length(); i++) {
					try {
						if (!(grid[row + i][col + i] == 0 || grid[row + i][col + i] == word.charAt(i)))
							continue outer;
					} catch (Exception e) {
						continue outer;
					}
				}
				isInsertable = true;
			} while (!isInsertable);

			// insert the word
			for (int i = 0; i < word.length(); i++) {
				if (grid[row + i][col + i] == word.charAt(i)) {
					intersectionCoords.add(new int[] { row + i, col + i });
					grid[row + i][col + i] = Character.toUpperCase(word.charAt(i));
				} else {
					grid[row + i][col + i] = word.charAt(i);
				}
			}
		}

		for (int placed = 1; placed <= howMany - howMany / 2; placed++) {
			String word = getWord(freeWords);
			int row;
			int col;
			boolean isInsertable = false;

			outer: do {
				// create random row and column
				row = getRandom(0, grid.length);
				col = getRandom(0, grid[0].length);

				// check if the word can fit
				for (int i = 0; i < word.length(); i++) {
					try {
						if (!(grid[row + i][col - i] == 0 || grid[row + i][col - i] == word.charAt(i)))
							continue outer;
					} catch (Exception e) {
						continue outer;
					}
				}
				isInsertable = true;
			} while (!isInsertable);

			// insert the word
			for (int i = 0; i < word.length(); i++) {
				if (grid[row + i][col - i] == word.charAt(i)) {
					intersectionCoords.add(new int[] { row + i, col - i });
					grid[row + i][col - i] = Character.toUpperCase(word.charAt(i));
				} else {
					grid[row + i][col - i] = word.charAt(i);
				}
			}
		}
	}

	public static void fillSpaces(char[][] grid) {
		// lower case intersections
		for (int[] coords : intersectionCoords) {
			grid[coords[0]][coords[1]] = Character.toLowerCase(grid[coords[0]][coords[1]]);
		}
		// fill blank spaces
		char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0)
					grid[i][j] = letters[getRandom(0, letters.length)];
			}
		}
	}

	public static void printGrid(char[][] grid) {
		StringBuilder padding = new StringBuilder();
		for (int i = 0; i < (grid.length - 1) * 2 + grid.length; i++) {
			padding.append("-");
		}

		System.out.println(padding);
		for (char[] letters : grid) {
			for (char letter : letters) {
				System.out.print(letter + "  ");
			}
			System.out.println();
		}

		System.out.println(padding);
		System.out.print("Words: ");
		for (int i = 0; i < usedWords.size(); i++) {
			if (i == usedWords.size() - 1)
				System.out.println(usedWords.get(i));
			else
				System.out.print(usedWords.get(i) + ", ");
		}
	}

	public static int[] getOrientations() {
		int[] orientations = new int[3];
		orientations[0] = getRandom(0, 12);
		orientations[1] = getRandom(0, 12 - orientations[0]);
		orientations[2] = 12 - orientations[1] - orientations[0];
		return orientations;
	}

	public static ArrayList<String> getWordList(String path) {
		try {
			String words = Files.readString(Path.of(path));
			words = words.toLowerCase();
			ArrayList<String> wordList = new ArrayList<String>();
			for (int i = 0, j = 0; i < words.length(); i++) {
				if (words.charAt(i) == '\n' || i == words.length() - 1) {
					String word = words.substring(j, i);
					if (word.length() <= 20)
						wordList.add(word);
					j = i + 1;
				}
			}
			return wordList;
		} catch (IOException e) {
			System.out.println("wscli: error: invalid filepath: " + path);
			System.exit(1);
			return null;
		}
	}

	public static String parseArgs(String[] args) {
		if (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help"))) {
			System.out.println("usage: wscli [-h] (-w <default words list> | -f <path to valid words file>)\n");
			System.out.println("Display a word search to the terminal.\n");
			System.out.println("arguments:");
			System.out.println("  -h, --help  Show this help message and exit.");
			System.out.println("  -w, --words Name of one of several default words lists that come with wscli.");
			System.out.println("			  Valid options: (animals, cars, countries, names, presidents)");
			System.out.println(
					"  -f, --file  Path to valid custom words file. Must be one word per line with maximum 20 characters.");
			System.exit(0);
		} else if (args.length == 2) {
			if (args[0].equals("-w") || args[0].equals("--words")) {
				String filePath = wscli.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				return filePath.substring(0, filePath.length() - 14) + "words/" + args[1] + "_words.txt";
			}
			if (args[0].equals("-f") || args[1].equals("--file")) {
				return args[1];
			}
		}
		System.out.print("wscli: error: invalid arguments: ");
		for (String arg : args) {
			System.out.print(arg + " ");
		}
		System.out.println();
		System.exit(1);
		return "";
	}

	public static String getWord(ArrayList<String> freeWords) {
		usedWords.add(freeWords.remove(getRandom(0, freeWords.size())));
		return usedWords.get(usedWords.size() - 1);
	}

	public static int getRandom(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}
}
