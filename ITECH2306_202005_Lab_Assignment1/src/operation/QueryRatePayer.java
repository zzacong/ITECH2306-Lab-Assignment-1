/**
 * 
 */
package operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Property;
import domain.RatePayer;

/**
 * @author Zac
 * @author Anush
 *
 */
public class QueryRatePayer extends FunctionalDialog {
	
	private static final int END = 0;
	private static final int MIN_RATE_PAYERS = 0;
	private int maxRatePayers;
	private static final String RATE_PAYER_PROMPT = "Which rate payer are we querying? \n";
	private String propertyPrompt;
	private String prompt;
	private ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>();
	private int ratePayer;
	private ArrayList<Property> listOfProperties = new ArrayList<Property>();
	ArrayList<Property> OwnedProperties;
	private static final int MAX_NO_USER_INPUTS = 1;

	public QueryRatePayer(Scanner console) {//throws FileNotFoundException, ClassNotFoundException, IOException {
		super(MAX_NO_USER_INPUTS, console);
		this.setListOfRatePayers();
		this.setListOfProperties();
	}

	@Override
	protected void obtainInput(int i) {
		switch (i) {
		case 0:
			maxRatePayers = listOfRatePayers.size(); 
			setPrompt(RATE_PAYER_PROMPT, 0);
			ratePayer = obtainIntInput(MIN_RATE_PAYERS, maxRatePayers, prompt);
			if (ratePayer == END) {
				setStillRunning(false);
			}
			break;
		}
	}

	public void setListOfRatePayers() {
		try(FileInputStream fis = new FileInputStream("files/Load_RatePayers.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);) 
		{
			System.out.println("\"Load_RatePayers.dat\" file is located \n");
			
			Object firstThing = ois.readObject(); 
			if (firstThing instanceof String) {
				System.out.println("First thing is a string: " + firstThing + "\n");
			}
			else {
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
					System.out.println("Next thing is not a RatePayer: " + nextThing);
				}
			}
			System.out.println("Number of Rate Payers: " + listOfRatePayers.size() + "\n");
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

	public void setListOfProperties() {
		try (FileInputStream fis = new FileInputStream("files/Load_Properties.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			System.out.println("\"Load_Properties.dat\" file is located \n");
			
			Object firstThing = ois.readObject(); 
			if (firstThing instanceof String) {
				System.out.println("First thing is a string: " + firstThing + "\n");
			}
			else {
				System.out.println("First string is not a string: " + firstThing);
			}
			
			while (fis.available() > 0) {
				Object nextThing = ois.readObject();
				if (nextThing instanceof Property) {
					System.out.println("Next thing is a Property");
					Property property = (Property) nextThing;
					listOfProperties.add(property);					
				}
				else {
					System.out.println("Next thing is not a Property: " + nextThing);
				}
			}
			
			System.out.println("Number of Properties: " + listOfProperties.size() + "\n");

		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("Load_Properties.dat file cannot be located for opening");
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
	
	public String getPrompt() {
		return this.prompt;
	}
	
	public void setPrompt(String description, int index) {
		String list = "";
		int i = 1;
		switch (index) {
		case 0:
			for (RatePayer rp : listOfRatePayers) {
				list += i + ". " + rp.getName() + "\n";
				i++;
			}
			list += END + ". To exit";
			break;
		case 1:
			for (Property p : OwnedProperties) {
				list += i + ". " + p.toString() + "\n";
				i++;
			}
			break;
		}
		this.prompt = description + list;
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
		RatePayer payer = listOfRatePayers.get(ratePayer-1);
		OwnedProperties = new ArrayList<Property>();
		for (Property property : listOfProperties) {
			if (property.getOwner().equals(payer))
				OwnedProperties.add(property);
		}
		
		propertyPrompt = "You have selected : " + payer + "\n" +
						 "The properties owned by " + payer.getName() + " are: \n";
		setPrompt(propertyPrompt, 1);
		System.out.println(prompt);
	}
}
