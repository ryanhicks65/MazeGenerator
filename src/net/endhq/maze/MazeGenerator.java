package net.endhq.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {
	private int height;
	private int width;
	private int[][] maze;
	public MazeGenerator(int height, int width) {
		this.height=height;
		this.width=width;
		this.maze=new int[height][width];
	}
	
	public int[][] generateMaze() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				maze[i][j] = 1;
		
		Random rand = new Random();
		int r = rand.nextInt(height);
		while (r % 2 == 0) {
			r = rand.nextInt(height);
		}
		int c = rand.nextInt(width);
		while (c % 2 == 0) {
			c = rand.nextInt(width);
		}
		maze[r][c] = 0;
		recursion(r, c);
		return maze;
	}

	private void recursion(int r, int c) {
		int[] randDirs = generateRandomDirections();
		for (int i = 0; i < randDirs.length; i++) {
			switch (randDirs[i]) {
			case 1: // Up
				// Whether 2 cells up is out or not
				if (r - 2 <= 0)
					continue;
				if (maze[r - 2][c] != 0) {
					maze[r - 2][c] = 0;
					maze[r - 1][c] = 0;
					recursion(r - 2, c);
				}
				break;
			case 2: // Right
				// Whether 2 cells to the right is out or not
				if (c + 2 >= width - 1)
					continue;
				if (maze[r][c + 2] != 0) {
					maze[r][c + 2] = 0;
					maze[r][c + 1] = 0;
					recursion(r, c + 2);
				}
				break;
			case 3: // Down
				// Whether 2 cells down is out or not
				if (r + 2 >= height - 1)
					continue;
				if (maze[r + 2][c] != 0) {
					maze[r + 2][c] = 0;
					maze[r + 1][c] = 0;
					recursion(r + 2, c);
				}
				break;
			case 4: // Left
				// Whether 2 cells to the left is out or not
				if (c - 2 <= 0)
					continue;
				if (maze[r][c - 2] != 0) {
					maze[r][c - 2] = 0;
					maze[r][c - 1] = 0;
					recursion(r, c - 2);
				}
				break;
			}
		}

	}
	private int[] generateRandomDirections() {
		ArrayList<Integer> randoms = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++)
			randoms.add(i + 1);
		Collections.shuffle(randoms);
		int[] ret = new int[4];
		ret[0]=randoms.get(0);
		ret[1]=randoms.get(1);
		ret[2]=randoms.get(2);
		ret[3]=randoms.get(3);
		return ret;
	}
}
