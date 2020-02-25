import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class AIGame {
	
	private String version;
	
	protected static int dimensions;
	
	protected static int [][] grid;
	
	public static void main(String[] args) throws IOException {
		 
	
		// takes in arguments in command prompt
		String path = args[0];
		
		Scanner keyboard = new Scanner(System.in); 
		
		System.out.println("Would you like to use sodoku or loshu");
		System.out.println("press 1 for Sudoku");
		System.out.println("press 2 for LoShu");
		
		
		int choice = keyboard.nextInt();
		if(choice == 1) {
				Sudoku sd = new Sudoku(path);
		}
		if(choice == 2) {
				LoShu ls = new LoShu(path);
		}

	}
	
	protected int[][] solve(){
		
		
		return grid;
		
	}
	
	public boolean isValid() {
		
		return false;
		
	}
	
	
	public String toString() {
		
		
		
		return null;
		
	}
	
	

}
