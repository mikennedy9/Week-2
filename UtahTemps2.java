package temps;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UtahTemps2 {
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		boolean dec = true;
        int selection = 0;
        
        do {
        	System.out.println("Enter 1 to view November temperatures, enter 2 for December: ");
        	selection = scanner.nextInt();
        	
        	if(selection == 1) {
        		dec = false;
        		}
        	
        } while(selection != 1 && selection != 2);
        String sql;
        
        if(!dec) {
        	sql = "SELECT month, day, year, hi, lo FROM temperatures WHERE month = 11 AND year = 2020 ORDER BY month, day, year";
        }
        else { 
        	sql = "SELECT month, day, year, hi, lo FROM temperatures WHERE month = 12 AND year = 2020 ORDER BY month, day, year"; 
		}
		
		String connectionString = "jdbc:mysql://127.0.0.1:3306/practice";
        String dbLogin = "root";
        String dbPassword = "Pl@t1num9";
        Connection conn = null;
	
        try {
            conn = DriverManager.getConnection(connectionString, dbLogin, dbPassword);
           
			if(conn != null) {
				 try (Statement stmt = conn.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs = stmt.executeQuery(sql)) 
                {

				int numRows;
				int numCols = 5;
				rs.last();
				numRows = rs.getRow();
				rs.first();
				
				String[][] matrix = new String[numRows][numCols];
                
				for(int i = 0; i < numRows; i++) {
					matrix[i][0] = rs.getString("month");
					matrix[i][1] = rs.getString("day");
					matrix[i][2] = rs.getString("year");
					matrix[i][3] = rs.getString("hi");
					matrix[i][4] = rs.getString("lo");
					rs.next();
				}

					PrintStream o = new PrintStream(new File("TemperaturesReportFromDB.txt"));
					System.setOut(o);
					String month = "";
					if(dec) {
						month = "December";
					}else {
						month = "November";
					}
					 
					System.out.println("-------------------------------------------------------------");
					System.out.println(month + " 2020: Temperaturs in Utah");
					System.out.println("-------------------------------------------------------------");
					System.out.println("Day           " + " High     " + "Low  " + "   Variance");
					System.out.println("-------------------------------------------------------------");

						int highTemps = 0;
						int lowTemps = 0;
						int count = 0;
						int highestTemp = 0;
						int highestDay = 0;
						int highestMonth = 0;
						int lowestDay = 0;
						int lowestMonth =0;
						int lowestTemp = 100;
					
				       for (int i = 0; i < matrix.length; i++)
	                    {
				    	   int variance = Integer.parseInt(matrix[i][3]) - Integer.parseInt(matrix[i][4]);
				    	   String space = "    ";
				    	   
				    	   highTemps += Integer.parseInt(matrix[i][3]);
				    	   lowTemps += Integer.parseInt(matrix[i][4]);
				    	   count++;
				    	   
				    	   if(Integer.parseInt(matrix[i][3]) > highestTemp) {
				    		   highestTemp = Integer.parseInt(matrix[i][3]);
				    		   highestDay =  Integer.parseInt(matrix[i][1]);
				    		   highestMonth =  Integer.parseInt(matrix[i][0]);
				    	   }
				    	   if(Integer.parseInt(matrix[i][4]) < lowestTemp) {
				    		   lowestTemp = Integer.parseInt(matrix[i][4]);
				       		   lowestDay =  Integer.parseInt(matrix[i][1]);
				    		   lowestMonth =  Integer.parseInt(matrix[i][0]);
				    	   }
				    	   
                        System.out.printf("%s/%s/%s\t%s\t%s\t%s%d%n", 
                        matrix[i][0],
                        matrix[i][1],
                        matrix[i][2],
                        matrix[i][3],
                        matrix[i][4],
                        space,
                        variance);
	                    }
				       
				     int avgHighTemp = highTemps / count;
				     int avgLowTemp = lowTemps / count;
				      
				       
					System.out.println("-------------------------------------------------------------");
					System.out.println(month + " Highest Temp: " + highestMonth + "/" + highestDay + ": " + highestTemp + " Average Hi: " + avgHighTemp);
					System.out.println(month + " Lowest Temp: " + lowestMonth + "/" + lowestDay + ": " + lowestTemp + " Average Low: " + avgLowTemp);
						

					System.out.println("-------------------------------------------------------------");
					System.out.println("Graph");
					System.out.println("-------------------------------------------------------------");
					System.out.println("1   5    10    15    20    25    30    35    40    45    50");
					System.out.println("|   |    |     |     |     |     |     |     |     |     |");
					System.out.println("-------------------------------------------------------------");
					
					
					for(int i = 0; i < matrix.length; i++ ) {
						int day = Integer.parseInt(matrix[i][1]);
						int high = Integer.parseInt(matrix[i][3]);
						int low = Integer.parseInt(matrix[i][4]);
						
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
	
				}catch (SQLException ex) {
					System.out.println("Database connection failed");
				}
			}

		}catch(Exception e) {
			System.out.println("Database Connection failed.");
			e.printStackTrace();
		}
	
	}
}
	 
	 
	 
	 
	 
