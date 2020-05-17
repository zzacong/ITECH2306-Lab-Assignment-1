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
	
	public LoadRatePayers() {
	}

	public ArrayList<RatePayer> getListOfRatePayers() {
		return this.listOfRatePayers;
	}
	
	private void setListOfRatePayers() {
		
		String name = null;
		String address = null;
		String postcode = null;
		String phone = null;
		String type = null;
		boolean charity = false;
		int column = 0;
		
		System.out.println("Setting up list of Rate Payers...");

		try (Scanner fileScanner = new Scanner(new File(LOAD_RATEPAYERS_CSV));
			FileOutputStream fos = new FileOutputStream(new File(LOAD_RATEPAYERS_DAT));
			ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{	
			System.out.println("\"ITECH2306_2020_Load_RatePayers.csv\" is located \n");
			
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
					if (!rowScanner.hasNext()) {
						column = 0; 
					}
					else { 
						column++;
					}
				}
				RatePayer payer = new RatePayer(name, address, postcode, phone, type, charity);
				listOfRatePayers.add(payer);
				System.out.println(payer);
				rowScanner.close();
			}
			oos.writeObject("List of Rate Payers");
			for (RatePayer rp : listOfRatePayers) {
				oos.writeObject(rp);
			}
			System.out.println("Number of Rate Payers: " + listOfRatePayers.size() + "\n");
			System.out.println("Serializable file \"Load_RatePayers.dat\" is created");
		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("ITECH2306_2020_Load_RatePayers.csv OR Load_RatePayers.dat file cannot be located for opening");
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

	public void loadListOfRatePayers() {
			
		System.out.println("Loading list of Rate Payers...");
		
		try(FileInputStream fis = new FileInputStream(LOAD_RATEPAYERS_DAT); 
			ObjectInputStream ois = new ObjectInputStream(fis);) 
		{
			System.out.println("\"Load_RatePayers.dat\" file is located \n");
			Object firstThing = ois.readObject(); 
			if (firstThing instanceof String) {
				System.out.println("Loaded file: " + firstThing);
			}
			else {
				// Throw some exceptions here
				System.out.println("First string is not a string: " + firstThing);
			}
			
			while (fis.available() > 0) {
				Object nextThing = ois.readObject();
				if (nextThing instanceof RatePayer) {
					System.out.println("Next thing is a RatePayer");
					RatePayer payer = (RatePayer) nextThing;
					listOfRatePayers.add(payer);
				}
				else {
					// Throw some exceptions here
					System.out.println("Next thing is not an ArrayList: " + nextThing);
				}
			}
			System.out.println("Number of Rate Payers: " + listOfRatePayers.size() + "\n");
		} 
		catch(FileNotFoundException fnfExc) {
			System.out.println("Unable to locate Load_RatePayers.dat file for opening");
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
		LoadRatePayers loadRatePayers = new LoadRatePayers();
		loadRatePayers.setListOfRatePayers();
	}

}
