package temps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;





public class UtahTemps {
	
	
	public static void main(String[] args) throws IOException {
		
		File fileName = new File("C:\\\\Users\\\\Admin\\\\eclipse-workspace\\\\Assignment 2\\\\src\\\\temps\\\\SLCDecember2020Temperatures.csv");
		
		if(fileName.exists()) {
			BufferedReader br = null; 
			String line = "";
			br = new BufferedReader(new FileReader(fileName));
			List<List<String>> lines = new ArrayList<>();
			int variance = 0;
			final int rows = 31;
			final int cols = 3;
			String[][] matrix = new String[rows][cols];
			int row = 0;
			
				while ((line = br.readLine()) != null){
				    if ( line.isEmpty() ) {
				        continue;
				    }

					String[] items = line.split(",");
						for(int col = 0; col < cols; col++) {
							matrix[row][col] = items[col];
							
						}
						row++;
				}

				 PrintStream o = new PrintStream(new File("TemperaturesReport.txt"));
				 System.setOut(o);
				 
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("December 2020: Temperaturs in Utah");
				System.out.println("-------------------------------------------------------------");
				System.out.println("Day  " + "High  " + "Low  " + "Variance");
				System.out.println("-------------------------------------------------------------");
				
				for(int i = 0; i < 31; i++) {
					for(int j = 0; j < 3; j++) {
						System.out.print(matrix[i][j] + "     ");
					}
					System.out.println();
				}
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("Graph");
				System.out.println("-------------------------------------------------------------");
				System.out.println("1   5    10    15    20    25    30    35    40    45    50");
				System.out.println("|   |    |     |     |     |     |     |     |     |     |");
				System.out.println("-------------------------------------------------------------");
				
				
				for(int i = 0; i < matrix.length; i++ ) {
					int day = Integer.parseInt(matrix[i][0]);
					int high = Integer.parseInt(matrix[i][1]);
					int low = Integer.parseInt(matrix[i][2]);
					
					String plusRepeat = new String(new char[high]).replace("\0", "+");
					String minusRepeat = new String(new char[low]).replace("\0", "-");
					
					System.out.println();
					System.out.print(day + " Hi " + plusRepeat);

					System.out.println();
					System.out.print("   " + "Lo " + minusRepeat);
				}

				System.out.println();
				System.out.println("-------------------------------------------------------------");
				System.out.println("|   |    |     |     |     |     |     |     |     |     |");
				System.out.println("1   5    10    15    20    25    30    35    40    45    50");

			}

	}
	
}
	 
	 
	 
	 
	 
