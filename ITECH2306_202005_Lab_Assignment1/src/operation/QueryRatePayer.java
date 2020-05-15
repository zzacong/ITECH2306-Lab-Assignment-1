/**
 * 
 */
package operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import domain.RatePayer;

/**
 * @author Zac
 *
 */
public class QueryRatePayer extends FunctionalDialog {
	
	private static final int END = 0;
	private static final int MIN_RATE_PAYERS = 0;
	private int maxRatePayers;
	private static final String RATE_PAYER_PROMPT = "Which rate payer are we querying? \n";
	private String prompt;
	private ArrayList<RatePayer> listOfRatePayers;
	private int ratePayer;
	private static final int MAX_NO_USER_INPUTS = 1;

	public QueryRatePayer(Scanner console) {//throws FileNotFoundException, ClassNotFoundException, IOException {
		super(MAX_NO_USER_INPUTS, console);
		this.setListOfRatePayers();
	}

	@Override
	protected void obtainInput(int i) {
//		try {
			switch (i) {
			case 0:
//				FileInputStream fis = new FileInputStream("files/Load_RatePayers.dat");
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				System.out.println("\"Load_RatePayers.dat\" file is located \n");
//				
//				Object firstThing = ois.readObject(); 
//				if (firstThing instanceof String) {
//					System.out.println("First thing is a string: " + firstThing + "\n");
//				}
//				else {
//					System.out.println("First string is not a string: " + firstThing);
//				}
//				
//				listOfRatePayers = new ArrayList<RatePayer>();
//				while (fis.available() > 0) {
//					Object nextThing = ois.readObject();
//					if (nextThing instanceof RatePayer) {
//						System.out.println("Next thing is a RatePayer");
//						RatePayer payer = (RatePayer) nextThing;
//						listOfRatePayers.add(payer);					
//					}
//					else {
//						System.out.println("Next thing is not an ArrayList: " + nextThing);
//					}
////					System.out.println("Input stream available: " + ois.available());
////					System.out.println("Input file available: " + fis.available() + "\n");
//				}
//				
//				System.out.println("ArrayList length: " + listOfRatePayers.size() + "\n");
//				ois.close();
//				fis.close();
				
				maxRatePayers = listOfRatePayers.size(); 
				setPrompt(RATE_PAYER_PROMPT);
				ratePayer = obtainIntInput(MIN_RATE_PAYERS, maxRatePayers, prompt);
				if (ratePayer == END)
					setStillRunning(false);
				break;
			}
//		} 
//		catch(FileNotFoundException fnfExc) {
//			System.out.println("Load_RatePayers.dat file cannot be located for opening");
//			fnfExc.printStackTrace();
//		}
//		catch(IOException ioExc) {
//			System.out.println("Problem with file processing: " + ioExc.getMessage());
//			ioExc.printStackTrace();
//		}
//		catch(Exception otherExc) {
//			System.out.println("Something went wrong: " + otherExc.getMessage());
//			otherExc.printStackTrace();
//		}
//		finally {
//			
//		}
	}

	public ArrayList<RatePayer> getListOfRatePayers() {
		return listOfRatePayers;
	}

	public void setListOfRatePayers() {//throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream("files/Load_RatePayers.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			System.out.println("\"Load_RatePayers.dat\" file is located \n");
			
			Object firstThing = ois.readObject(); 
			if (firstThing instanceof String) {
				System.out.println("First thing is a string: " + firstThing + "\n");
			}
			else {
				System.out.println("First string is not a string: " + firstThing);
			}
			
			listOfRatePayers = new ArrayList<RatePayer>();
			while (fis.available() > 0) {
				Object nextThing = ois.readObject();
				if (nextThing instanceof RatePayer) {
					System.out.println("Next thing is a RatePayer");
					RatePayer payer = (RatePayer) nextThing;
					listOfRatePayers.add(payer);					
				}
				else {
					System.out.println("Next thing is not an ArrayList: " + nextThing);
				}
			}
			
			System.out.println("ArrayList length: " + listOfRatePayers.size() + "\n");
			ois.close();
			fis.close();
		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("Load_RatePayers.dat file cannot be located for opening");
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

	public void setPrompt(String prompt) {
		String choices = "";
		int i = 1;
		for (RatePayer rp : listOfRatePayers) {
			choices += i + ". " + rp.getName() + "\n";
			i++;
		}
		this.prompt = prompt + choices + END + ". To exit";
	}

	private int obtainIntInput(int min, int max, String prompt) {
		System.out.println(prompt);
		return validateInt(min, max);
	}
	
	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection ("+min + "-" + max +"):");
			if (!getScanner().hasNextInt())
				userInput = max+1;
			else
				userInput = getScanner().nextInt();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput;
	}
	
	@Override
	protected void respondToInput() {
		
	}
}
