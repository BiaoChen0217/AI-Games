
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoShu extends AIGame{
	
	//an array list of all the numbers in the txt file that are not zero
	
	ArrayList<Integer> gridarraylist = new ArrayList<Integer>();
	
	// second grid to restart the solving method
	
	private int [][] restart = new int[5][5];
	
	// cheat why to get 2d dimenshions
	
	int dimenshion;
	
	public LoShu(String path) throws IOException {
		
		readPuzzle(path);
		
	}
	
	
	
	/**
	 * 
	 * @param filePath
	 * @return int [][] grid ;// a 2d array of the read file
	 * @throws IOException
	 */
	
	public int[][] readPuzzle(String filePath) throws IOException{
		
		Scanner keyboard = new Scanner(System.in); 
		
		Scanner sc = new Scanner(new File(filePath)); 
		
		System.out.println("How big is the 2D array odd numbers only");
		
		 dimenshion = keyboard.nextInt();
		
		// sets up a 5x5 grid 
		grid = new int[dimenshion][dimenshion];
		
		
		// if the txt file has a next line then it gose in to add the next int in the line to the 2d array
		if(sc.hasNextLine()) {
			
	
		for (int row = 0; row < dimenshion; row++) {
			for (int column = 0; column < dimenshion; column++) {
				if (sc.hasNextInt()) {
		
					grid[row][column] = sc.nextInt();
					
					// creates copy of grid to reset the origal grid later
					
					restart [row][column] = grid[row][column];
					
					
					// adds all the numbers that are not zero to a list so later i can check if 
					//the random number generated is equal to any of them
					
					if (grid[row][column] != 0) {
					gridarraylist.add(grid[row][column]);
					}
					
					}
				}
			}
		}
		sc.close();
				
		solve();
		
		toString();
		
		return grid;
		
	}
	
	/**  
	 * @return int [][] grid ;// a 2d array of the read file
	 * 
	 * description a function taht solves the 5x5 grid that was created in the read puzzle function
	 * 
	*/
	
	
	@Override
	public int[][] solve(){
		
		Random rand = new Random();
		
		boolean notSolved = false;
		
		
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		// fills array list with cannidates 
		
		for(int x = 1; x < 26; x++) {
			
			candidates.add(x);
		}
		
		// removes numbers already in the square
		// exception to zeros
		
		
		for (int row = 0; row < dimenshion; row++) {
			for (int column = 0; column < dimenshion; column++) {
				
				
				
				if (grid[row][column] != 0) {
					if(candidates.contains(grid[row][column])) {
						
						int i  = candidates.indexOf(grid[row][column]);
						
						candidates.remove(i);
					}
				}
				
				// itterates over and over to try to find a solution
				
				while(notSolved == false) {
					
					
					
					for (int r = 0; r < dimenshion; r++) {
						for (int col = 0; col < dimenshion; col++) {
					
								
								if (grid[r][col] == 0) {
								
									
								// gets a random number that is in the candidates list and puts it in temp
								int temp = candidates.get(rand.nextInt((dimenshion*dimenshion)-1) + 1);
								
								
								// checks if the number in the array list is the loshu square already
								while(gridarraylist.contains(temp)) {
									
									temp = candidates.get(rand.nextInt((dimenshion*dimenshion)-1) + 1);
																		
								}
									// sets the temp number as the cell if it passes the tests
								
									grid[r][col] = temp;
								
							}
						}
					}					
					notSolved = isMagicSquare(grid);
					
					toString();
					
					System.out.println("-------------");
							
					// if it fails to solve it on this itteration it resets the grid and starts over again
					
					for (int r = 0; r < dimenshion; r++) {
						for (int col = 0; col < dimenshion; col++) {
							grid[r][col] = restart[r][col];
						}
					}
					
					
				}	
			}
		}
		
		
		return grid;

	}
	
	/**
	 * @return String of 2d array 
	 */
	
	
	@Override
	public String toString() {
		
		for (int row = 0; row < dimenshion; row++) {
			for (int column = 0; column < dimenshion; column++) {
				
				System.out.print(grid[row][column] + " ");

			}
			System.out.println();
		}
		return null;
		
	}
	
	
	
	static boolean isMagicSquare(int array[][]) 
    { 
		boolean isMagic = false;
		
		// Calculate sum of rows
		int firstRow = sumRow(array, 0);
		int secondRow = sumRow(array, 1);
		int thirdRow = sumRow(array, 2);
		int fourthRow = sumRow(array, 3);
		int fifthRow = sumRow(array, 4);
		
		// Calculate sum of columns
		int firstCol = sumCol(array, 0);
		int secondCol = sumCol(array, 1);
		int thirdCol = sumCol(array, 2);
		int fourthCol = sumRow(array, 3);
		int fifthCol = sumRow(array, 4);
		

		// Calculate sum of diagonals
		int sumDiagonal1 = sumPrimaryDiagonal(array);
		int sumDiagonal2 = sumSecondDiagonal(array);
		
		// Determine if all sums equal each other
		
		if(firstRow == 65) {
				if(firstRow == secondRow && firstRow == thirdRow && firstRow == fourthRow && 
					firstRow == fifthRow &&
					firstRow == firstCol && firstRow == secondCol && firstRow == thirdCol && 
					firstRow == fourthCol && firstRow == fifthCol &&
					firstRow == sumDiagonal1 && firstRow == sumDiagonal2)
				{
					isMagic = true;
				}
				
		}
		else
		{
			isMagic = false;
		}
		
		return isMagic;
    
    } 
	
	
	public static int sumRow(int[][] array, int row)
	{
		int rowSum = 0;
		
		for(int col = 0; col < array[0].length; col++)
		{
			rowSum += array[row][col];
		}
		
		return rowSum;
	}
	
	public static int sumCol(int[][] array, int col)
	{
		int colSum = 0;
		
		for(int row = 0; row < array.length; row++)
		{
			colSum += array[row][col];
		}
		
		return colSum;
	}
	
	public static int sumPrimaryDiagonal(int[][] array)
	{
		int diagonalSum1 = 0;
		
		for(int row = 0; row < array.length; row++)
		{
			for(int col = 0; col < array[0].length; col++)
			{
				if(row == col) // row must equal column to be a diagonal
				{
					diagonalSum1 += array[row][col];
				}
			}
		}
		
		return diagonalSum1;
	}
	
	public static int sumSecondDiagonal(int[][] array)
	{
		int diagonalSum1 = 0;
		
		for(int row = 0; row < array.length; row++)
		{
			for(int col = 0; col < array[0].length; col++)
			{
				// row must be equal to the number of rows - column - 1 to be diagonal
				if(row == array.length - col - 1) 
				{
					diagonalSum1 += array[row][col];
				}
			}
		}
		
		return diagonalSum1;
	}
	

}
