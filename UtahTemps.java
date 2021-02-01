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

//				 PrintStream o = new PrintStream(new File("TemperaturesReport.txt"));
//				 System.setOut(o);
				 
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("December 2020: Temperaturs in Utah");
				System.out.println("-------------------------------------------------------------");
				System.out.println("Day  " + "High  " + "Low  " + "Variance");
				System.out.println("-------------------------------------------------------------");
				
				for(int i = 0; i < matrix.length; i++ ) {
					int high = Integer.parseInt(matrix[i][1]);
					int low = Integer.parseInt(matrix[i][2]);
					
				}
				
				int variance = 0;
				int highTemps = 0;
				int lowTemps = 0;
				int count = 0;
				int highestTemp = 0;
				int highestDay = 0;
				int highestMonth = 12;
				int lowestTemp = 100;
				int lowestDay = 0;
				int lowestMonth = 12;
				int avgHighTemp = 0;
				int avgLowTemp = 0;
				
				for(int i = 0; i < 31; i++) {
					int high = Integer.parseInt(matrix[i][1]);
					int low = Integer.parseInt(matrix[i][2]);
					variance = high - low;
					highTemps += Integer.parseInt(matrix[i][1]);
					lowTemps += Integer.parseInt(matrix[i][2]);
					count++;
					
					if(high > highestTemp) {
						highestTemp = high; 
						highestDay = Integer.parseInt(matrix[i][0]);
					}
					
					if(low < lowestTemp) {
						lowestTemp = low; 
						lowestDay = Integer.parseInt(matrix[i][0]);
					}
					
					for(int j = 0; j < 3; j++) {				
						System.out.print(matrix[i][j] + "     ");
					}
					System.out.print(variance);
					System.out.println();

				}
				
				avgHighTemp = highTemps / count;
				avgLowTemp = lowTemps / count;
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("December Highest Temp: " + highestMonth + "/" + highestDay + ": " + highestTemp + " Average Hi: " + avgHighTemp);
				System.out.println("December Lowest Temp: " + lowestMonth + "/" + lowestDay + ": " + lowestTemp + " Average Low: " + avgLowTemp);
				
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
	 
	 
	 
	 
	 
