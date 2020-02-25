

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class Sudoku {
    private int[][] matrix;
    static String path="s03a.txt" ;  //!!!!←change that
    
    
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
        
        s.backTrace(0, 0);
    }
 
    /**
     * Sudoku
     *Using backtrace
     * @param i row
     * @param j col
     */
    private void backTrace(int i, int j) {
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
                    backTrace(i, j + 1);
                    
                    //if the code can't continue,we need to change the first number to 0.
                    matrix[i][j] = 0;
                }
            }
        } else {
           
            backTrace(i, j + 1);
        }
    }
 
    /**
     *
     * @param row    
     * @param line   
     * @param number 
     * @return
     */
    private boolean check(int row, int line, int number) {
       
    	//make sure that there are not two same number
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }

        //check the sub-grid 
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
     * print
     */
    public void printArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}