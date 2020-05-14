/**
 * 
 */
package utility;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import domain.*;
/**
 * @author Zac
 *
 */
public class LoadRatePayers {

	public static void main(String[] args) {
		
		String name = null;
		String address = null;
		String postcode = null;
		String phone = null;
		String type = null;
		boolean charity = false;
		Scanner fileInput = null;
		int column = 0;
		
		try {
			File file = new File("files/ITECH2306_2020_Load_RatePayers.csv");
			fileInput = new Scanner(file);
			System.out.println("File is located");
			
			while (fileInput.hasNextLine()) {
				
				Scanner rowScanner = new Scanner(fileInput.nextLine());
				rowScanner.useDelimiter(",");
				
				while (rowScanner.hasNext()) {
					String data = rowScanner.next();
					switch (column) {
					case 0:
						name = data;
						break;
					case 1:
						address = data;
						break;
					case 2:
						postcode = data;
						break;
					case 3:
						phone = data;
						break;
					case 4:
						type = data;
						break;
					case 5:
						charity = (data.equalsIgnoreCase("true"))? true : false; 
						break;
					}
					if (column == 5) column = 0; 
					else column++;
				}
				
				RatePayer p = new RatePayer(name, address, postcode, phone, type, charity);
				ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>();
				listOfRatePayers.add(p);
				System.out.println(p);
				rowScanner.close();
			}
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Unable to locate the ITECH2306_2020_Load_RatePayers.csv file for opening");
			fnfe.printStackTrace();
		}
		finally {
			fileInput.close();
		}
	}

}
