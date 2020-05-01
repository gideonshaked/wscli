
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Gideon Shaked
 *
 */
public class Driver {
	private static ArrayList<String> usedWords = new ArrayList<String>();
	private static ArrayList<int[]> intersectionCoords = new ArrayList<int[]>();

	public static void main(String[] args) {
		printGrid(generateGrid(getWordList(), 20));
	}

	public static char[][] generateGrid(ArrayList<String> freeWords, int size) {
		int[] orientations = getOrientations();
		char[][] grid = new char[size][size];
		
		placeVerticals(orientations[0], grid, freeWords);
		placeHorizontals(orientations[1], grid, freeWords);
		placeDiagonals(orientations[2], grid, freeWords);

		fillSpaces(grid);

		return grid;
	}// end method generateGrid

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
	}// end method placeVerticals

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
	}// end method placeHorizontals

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
	}// end method placeDiagonals

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
	}// end method fillSpaces

	public static void printGrid(char[][] grid) {
		for (char[] letters : grid) {
			for (char letter : letters) {
				System.out.print(letter + "  ");
			}
			System.out.println();
		}
		System.out.println(usedWords.toString());
	}// end method printGrid

	public static int[] getOrientations() {
		int[] orientations = new int[3];
		orientations[0] = getRandom(0, 12);
		orientations[1] = getRandom(0, 12 - orientations[0]);
		orientations[2] = 12 - orientations[1] - orientations[0];
		return orientations;
	}// end method getOrientationNums

	public static ArrayList<String> getWordList() {
		try {
			String words = Files.readString(Path.of("src/words.txt"));
			words = words.toLowerCase();
			return new ArrayList<String>(Arrays.asList(words.split("\n")));
		} catch (IOException e) {
			System.out.println("There is no file named words.txt in src.");
			e.printStackTrace();
			return null;
		}
	}// end method getWordList

	public static String getWord(ArrayList<String> freeWords) {
		usedWords.add(freeWords.remove(getRandom(0, freeWords.size())));
		return usedWords.get(usedWords.size() - 1);
	}// end method getWord

	public static int getRandom(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}// end method getRandomInRange
}
