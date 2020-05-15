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
		ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>();
		Scanner fileScanner = null;
		int column = 0;
		
		try {
			File file = new File("files/ITECH2306_2020_Load_RatePayers.csv");
			fileScanner = new Scanner(file);
			System.out.println("\"ITECH2306_2020_Load_RatePayers.csv\" is located");
			
			while (fileScanner.hasNextLine()) {
				
				Scanner rowScanner = new Scanner(fileScanner.nextLine());
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
					if (!rowScanner.hasNext()) 
						column = 0; 
					else 
						column++;
//					name = rowScanner.next();
//					address = rowScanner.next();
//					postcode = rowScanner.next();
//					phone = rowScanner.next();
//					type = rowScanner.next();
//					charity = (rowScanner.next().equalsIgnoreCase("true"))? true : false;
				}
				
				RatePayer payer = new RatePayer(name, address, postcode, phone, type, charity);
				listOfRatePayers.add(payer);
				System.out.println(payer);
				rowScanner.close();
			}
			fileScanner.close();
			File binFile = new File("files/Load_RatePayers.dat");
			FileOutputStream fos = new FileOutputStream(binFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject("List of Rate Payers");
			for (RatePayer rp : listOfRatePayers) oos.writeObject(rp);
//			oos.writeObject(listOfRatePayers);
			
			oos.close();
			fos.close();
			System.out.println("Serializable file \"Load_RatePayers.dat\" is created");
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Unable to locate the ITECH2306_2020_Load_RatePayers.csv file for opening");
			fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			System.out.println("Problem with file processing: " + ioe.getMessage());
			ioe.printStackTrace();
		}
		catch(Exception otherExc) {
			System.out.println("Something went wrong: " + otherExc.getMessage());
			otherExc.printStackTrace();
		}
//		finally {
//		}
	}

}
