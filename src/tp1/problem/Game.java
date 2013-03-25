package tp1.problem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {

	public static final int MINE = -1;

	private int board[][];
	private int mineCount = 0;

	public Game(int board[][]) {

		this.board = board;
		countMines();
	}

	// create random board
	public Game(int width, int height, int mines) {

		mineCount = mines;
		board = new int[width][height];

		for (int[] col : board) {
			Arrays.fill(col, 0);
		}

		ArrayList<Integer> minePos = new ArrayList<Integer>(width * height);

		for (int i = 0; i < width * height; i++) {
			minePos.add(i, i);
		}

		Collections.shuffle(minePos);

		for (int mine = 0; mine < mines; mine++) {
			int pos = minePos.get(mine);
			board[pos % width][pos / width] = MINE;
		}

		fillNumbers();
	}

	// create board from file
	public Game(String fileName) throws FileNotFoundException, IOException, NumberFormatException {

		FileReader in = new FileReader(fileName);
		BufferedReader br = new BufferedReader(in);

		String[] dimensions = br.readLine().split(",");

		if (dimensions.length != 2) {
			throw new IOException();
		}

		int width = Integer.parseInt(dimensions[1]);
		int height = Integer.parseInt(dimensions[0]);

		board = new int[width][height];

		for (int y = 0; y < height; y++) {

			String[] lineNumbers = br.readLine().split(",");

			if (lineNumbers.length != width) {
				throw new IOException();
			}
			
			for (int x = 0; x < width; x++) {
				board[x][y] = lineNumbers[x].equals("*") ? MINE : 0;
			}
		}

		br.close();
		in.close();
		
		countMines();
		fillNumbers();
	}

	public int getHeight() {

		return board[0].length;
	}

	public int getWidth() {

		return board.length;
	}

	public int getMineCount() {

		return mineCount;
	}

	public int getPosition(int x, int y) {

		return board[x][y];
	}

	private void countMines() {

		for (int[] col : board) {
			for (int pos : col) {
				if (pos == MINE) {
					mineCount++;
				}
			}
		}
	}

	private void fillNumbers() {

		int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
		int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

		int width = board.length;
		int height = board[0].length;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[x][y] != MINE) {

					board[x][y] = 0;

					for (int d = 0; d < dx.length; d++) {

						int px = x + dx[d];
						int py = y + dy[d];

						if (px >= 0 && px < width && py >= 0 && py < height && board[px][py] == MINE) {
							board[x][y]++;
						}
					}
				}
			}
		}
	}
}
