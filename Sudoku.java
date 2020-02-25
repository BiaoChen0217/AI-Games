package shudu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 *@param path 
 *
 *
 *description:read the file path and write the puzzle to the array
 */
public class Sudoku {
    private int[][] matrix;
    static String path;
	private static Scanner input;
    
    public Sudoku(int[][] matrix) {
  	
        this.matrix = matrix;
    }
 
    
    
	public Sudoku(String path) throws FileNotFoundException {
		
		readPuzzle(path);
		
	}
	/**
	 * 
	 * @param filePath
	 * @return grid
	 * @throws FileNotFoundException
	 * 
	 * description: reads in the puzzle and sets all the cells in the grid 
	 * 
	 */
    
	public static int[][] readPuzzle(String path) throws FileNotFoundException{
		input = new Scanner(System.in);
    	System.out.println("Please input the puzzle path!Example:C:\\Users\\30133\\Desktop\\s01a.txt");
    	path=input.nextLine();
		Scanner sc = new Scanner(new File(path)); 
		int[][] grid = new int[9][9];
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (sc.hasNextInt()) {
					grid[row][column] = sc.nextInt();
				
					}
				}
			}
		
		sc.close();
		

		
		return grid;
		
	}
    public static void main(String[] args) throws FileNotFoundException {

        
        Sudoku s = new Sudoku(readPuzzle(path));
        
        s.solve(0, 0);
    }
 
    /**
     * Sudoku
     *Using backtrack
     * @param i row
     * @param j col
     */
    private void solve(int i, int j) {
        if (i == 8 && j == 9) {
            //if the array is finished,game over
            System.out.println("Game Over!");
            printArray();
            return;
        }
 
        
        //if the last one is read,change to next row
        if (j == 9) {
            i++;
            j = 0;
        }
 
        //if the number is 0,it will write the correct number
        
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
              
            	//check the number 
                if (check(i, j, k)) {
                    
                    matrix[i][j] = k;
                    solve(i, j + 1);
                    
                    //if the code can't continue,we need to change the first number to 0.
                    matrix[i][j] = 0;
                }
            }
        } else {
           
        	solve(i, j + 1);
        }
    }
 
    /**
     *
     * @param row    
     * @param line   
     * @param number 
     * 
     * function:cheak very row, line, sub-grid
     * 
     * 
     * 
     * @return 
     */
    private boolean check(int row, int line, int number) {
       
    	
        if(!isRowValid(row,number)||!isColumnValid(line,number)||!isSubGridValid(row,line,number))
        {
        	return false;
        }
       
        return true;
    }
 /**
  * 
  * 
  * @param row 
  * @param number
  * 
  * description:check very row to confirm there are not two same number
  * 
  */
    private boolean isRowValid(int row ,int number) {
    	for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number ) {
                return false;
            }
        }
		return true;
    }
    
    
    /**
     * 
     * 
     * @param line 
     * @param number
     * 
     * description:check very line to confirm there are not two same number
     * 
     */
    
    private boolean isColumnValid(int line ,int number) {
    	for (int i = 0; i < 9; i++) {
            if (matrix[i][line] == number ) {
                return false;
            }
        }
		return true;
    }
    
    /**
     * 
     * @param row
     * @param line 
     * @param number
     * 
     * description:check very sub-grid to confirm there are not two same number
     * 
     */
    private boolean isSubGridValid(int row,int line ,int number) {
    	int tempRow = row / 3;
        int tempLine = line / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * print the whole array,if true
     * @return if the problem can be solved,return true to AI Game.
     */
    public boolean printArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return true;
    }

}