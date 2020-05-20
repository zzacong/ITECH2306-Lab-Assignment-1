/**
 * 
 */
package operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import domain.RatePayer;

/**
 * @author Zac
 *
 */
public class RatePayerManager {
	
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
				System.out.println("First object is not an ArrayList: " + firstThing + "\n");
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
//			Runtime.getRuntime().exit(1);
		}
	}
}
