package FloodfillWithDFS;

import java.io.File;
import java.util.Scanner;

public class LakeCounting {

	static int count = 0;
	static int[][] checked = new int[10][12];
	static int[][] array = {{1,0,0,0,0,0,0,0,0,1,1,0},
							{0,1,1,1,0,0,0,0,0,1,1,1}, 
							{0,0,0,0,1,1,0,0,0,1,1,0},
							{0,0,0,0,0,0,0,0,0,1,1,0},
							{0,0,0,0,0,0,0,0,0,1,0,0},
							{0,0,1,0,0,0,0,0,0,1,0,0},
							{0,1,0,1,0,0,0,0,0,1,1,0},
							{1,0,1,0,1,0,0,0,0,0,1,0},
							{0,1,0,1,0,0,0,0,0,0,1,0},
							{0,0,1,0,0,0,0,0,0,0,1,0}};
	
	public static void main(String[] args) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++)
			{
				if (array[i][j] == 0)
					checked[i][j] = 1;
			}
		}
		System.out.println(array[0].length);
		System.out.println(array.length);
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++)
			{
				if (checked[i][j] == 0) {
					count++;
					floodfill(i,j);
				}
			}
		}
		System.out.println(count);
	}
	
	
	public static void floodfill(int row, int col) {
		if (row < 0 || col < 0 || col >= array[0].length || row >= array.length)
			return;
		if (array[row][col] == 0)
			return;
		if (checked[row][col] == 1)
			return;
		
		checked[row][col] = 1;
		floodfill(row + 1, col + 1);
		floodfill(row + 1, col);
		floodfill(row + 1, col - 1);
		floodfill(row, col + 1);
		floodfill(row, col - 1);
		floodfill(row - 1, col + 1);
		floodfill(row - 1, col);
		floodfill(row - 1, col - 1);
	}

}
