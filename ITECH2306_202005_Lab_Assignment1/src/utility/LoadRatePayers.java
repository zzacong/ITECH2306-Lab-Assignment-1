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
 * @author Anush
 *
 */
public class LoadRatePayers {

	static final String LOAD_RATEPAYERS_CSV = "files/ITECH2306_2020_Load_RatePayers.csv";
	static final String LOAD_RATEPAYERS_DAT = "files/Load_RatePayers.dat";

	private ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>();
	
	public ArrayList<RatePayer> getListOfRatePayers() {
		return this.listOfRatePayers;
	}

	public void loadListOfRatePayers() {
			
		System.out.println("Loading list of Rate Payers...");
		
		try(FileInputStream fis = new FileInputStream(LOAD_RATEPAYERS_DAT); 
			ObjectInputStream ois = new ObjectInputStream(fis);) 
		{
			System.out.println("\"Load_RatePayers.dat\" file is located");
			Object firstThing = ois.readObject(); 
			if (firstThing instanceof ArrayList<?>) {
				this.listOfRatePayers = (ArrayList<RatePayer>) firstThing;
				System.out.println("Number of Rate Payers: " + listOfRatePayers.size() + "\n");
			}
			else {
				// Throw exception here
				System.out.println("First string is not an ArrayList: " + firstThing);
			}
		} 
		catch(FileNotFoundException fnfExc) {
			System.out.println("Unable to locate file for opening: " + fnfExc.getMessage());
			fnfExc.printStackTrace();
		}
		catch(IOException ioExc) {
			System.out.println("Problem with file processing: " + ioExc.getMessage());
			ioExc.printStackTrace();
		}
		catch(Exception otherExc) {
			System.out.println("Something went wrong: " + otherExc.getMessage());
			otherExc.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>();
		String name = null;
		String address = null;
		String postcode = null;
		String phone = null;
		String type = null;
		String charity = null;
		
		
		System.out.println("Setting up list of Rate Payers...");

		try (Scanner fileScanner = new Scanner(new File(LOAD_RATEPAYERS_CSV));
			FileOutputStream fos = new FileOutputStream(new File(LOAD_RATEPAYERS_DAT));
			ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{	
			System.out.println("\"ITECH2306_2020_Load_RatePayers.csv\" is located \n");
			
			while(fileScanner.hasNextLine()) {
				try {
					Scanner rowScanner = new Scanner(fileScanner.nextLine());
					rowScanner.useDelimiter(",");
					
					int column = 0;
					while(rowScanner.hasNext()) {
						String stringData;
						stringData = rowScanner.next();
						stringData = stringData.trim();
						switch(column) {
						case 0:
							name = stringData;
							break;
						case 1:
							address = stringData;
							break;
						case 2:
							postcode = stringData;
							break;
						case 3:
							phone = stringData;
							break;
						case 4:
							type = stringData;
							break;
						case 5:
							charity = stringData;
							break;
						}
						
						column = (rowScanner.hasNext())? ++column : 0;
					}
					RatePayer payer = new RatePayer(name, address, postcode, phone, type, charity);
					
					listOfRatePayers.add(payer);
					System.out.println(payer);
					
					rowScanner.close();
				}
				catch(NullPointerException npExc) {
					System.out.println(npExc.getMessage());
				}
				catch(IllegalArgumentException iaExc) {
					System.out.println(iaExc.getMessage());
				}
			}
			oos.writeObject(listOfRatePayers);
			System.out.println("Number of Rate Payers: " + listOfRatePayers.size() + "\n");
			System.out.println("Serializable file \"Load_RatePayers.dat\" is created");
		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("Unable to locate file for opening: " + fnfExc.getMessage());
			fnfExc.printStackTrace();
		}
		catch(IOException ioExc) {
			System.out.println("Problem with file processing: " + ioExc.getMessage());
			ioExc.printStackTrace();
		}
		catch(Exception otherExc) {
			System.out.println("Something went wrong: " + otherExc.getMessage());
			otherExc.printStackTrace();
		}
	}

}
