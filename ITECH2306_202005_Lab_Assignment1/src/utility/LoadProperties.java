/**
 * 
 */
package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import domain.*;

/**
 * @author Zac
 * @author Anush
 *
 */
public class LoadProperties {

	static final int RESIDENTIAL = 0;
	static final int COMMERCIAL = 1;
	static final int VACANT_LAND = 2;
	static final int HOSPITAL = 3;
	static final int INDUSTRIAL = 4;
	static final int SCHOOL_COMMUNITY = 5;
	static final int OTHER = 6;
	static final String TRUE = "TRUE";
	
	public static void main(String[] args) {
		
		ArrayList<String> propertyTypes = new ArrayList<String>();
		propertyTypes.add("Residential");
		propertyTypes.add("Commercial");
		propertyTypes.add("VacantLand");
		propertyTypes.add("Hospital");
		propertyTypes.add("Industrial");
		propertyTypes.add("SchoolCommunity");
		propertyTypes.add("Other");
		
		String propertyType = null;
		String description = null;
		String location = null;
		double area = 0;
		double siteValue = 0;
		double capitalImprovedValue = 0;
		double capitalImprovedRate;
		double netAnnualValue = 0;
		String valuationDate = null;
		RatePayer owner = null;
		String extraAttr1 = null;
		String extraAttr2= null;
		int extraAttr3 = 0;
		boolean extraBooleanAttr = false;
		
		ArrayList<Property> listOfProperties = new ArrayList<Property>();
		ArrayList<RatePayer> listOfRatePayers = new ArrayList<RatePayer>(); 
		
		int column = 0;
		
		try (Scanner fileScanner = new Scanner(new File("files/ITECH2306_2020_Load_Properties.csv"));
			FileInputStream fis = new FileInputStream("files/Load_RatePayers.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			FileOutputStream fos = new FileOutputStream(new File("files/Load_Properties.dat"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);){
			
			System.out.println("\"ITECH2306_2020_Load_Properties.csv\" file is located");
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
//					listOfRatePayers = (ArrayList<RatePayer>) nextThing;
					
				}
				else {
					System.out.println("Next thing is not an ArrayList: " + nextThing);
				}
				System.out.println("Input stream available: " + ois.available());
				System.out.println("Input file available: " + fis.available() + "\n");
			}
			
			System.out.println("ArrayList length: " + listOfRatePayers.size() + "\n");
						
			while (fileScanner.hasNextLine()) {
				Scanner rowScanner = new Scanner(fileScanner.nextLine());
				rowScanner.useDelimiter(",");
				
				while (rowScanner.hasNext()) {
					String dataStr = null;
					double dataDbl = 0;
					
	
					if ((column >= 0 && column <= 2) || (column >= 8 && column <= 11)) 
						dataStr = rowScanner.next();
					else
						dataDbl = rowScanner.nextDouble();
					
					
					switch (column) {
					case 0:
						propertyType = dataStr;
						break;
					case 1:
						description = dataStr;
						break;
					case 2:
						location = dataStr;
						break;
					case 3:
						area = dataDbl;
						break;
					case 4:
						siteValue = dataDbl;
						break;
					case 5:
						capitalImprovedValue = dataDbl;
						break;
					case 6:
						capitalImprovedRate = dataDbl; 
						break;
					case 7:
						netAnnualValue = dataDbl;
						break;
					case 8:
						valuationDate = dataStr;
						break;
					case 9:
						for (RatePayer payer: listOfRatePayers) {
							if (payer.getName().equalsIgnoreCase(dataStr)) {
								owner = payer;
								break;
							}
						}
						break;
					case 10:
						if (propertyType.equalsIgnoreCase("Hospital")) {
							extraBooleanAttr = (dataStr.equalsIgnoreCase(TRUE))? true : false;
						}
						else {
							extraAttr1 = dataStr ;
						}
									
							
						break;
					case 11:
						if (propertyType.equalsIgnoreCase("Industrial")) {
							extraBooleanAttr = (dataStr.equalsIgnoreCase(TRUE))? true : false;
						}
						else {
							extraAttr2 = dataStr ;
						}
						break;
					case 12:
						extraAttr3 =(int) dataDbl;
						break;
					}
					if (rowScanner.hasNext()) {
						column++; 
					}
					else {
						column = 0;
					}
				}
				
				
				
				Property property = null;
				if (propertyTypes.contains(propertyType)) {
					switch (propertyTypes.indexOf(propertyType)) {
					case(RESIDENTIAL):
						property = new Residential(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
//						System.out.println("Residential property type created");
//						System.out.println(property);
						break;
					case(COMMERCIAL):
						property = new Commercial(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
//						System.out.println("Commercial property type created");
//						System.out.println(property);
						break;
					case(VACANT_LAND):
						property = new VacantLand();
//						System.out.println("Vacant Land is not yet implemented");
						break;
					case(HOSPITAL):
						property = new Hospital(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraBooleanAttr, extraAttr2, extraAttr3);
//						System.out.println("Hospital property type created");	
						
						break;
					case(INDUSTRIAL):
						property = new Industrial(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraBooleanAttr);
//						System.out.println("Industrial property type created");
						break;
					case(SCHOOL_COMMUNITY):
						property = new SchoolCommunity(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
//						System.out.println("School Community property type created");
						break;
					case(OTHER):
						property = new OtherProperty();
//						System.out.println("Other property type created");	
						break;	
					}
				}
				
				System.out.println(property);
//				System.out.println(propertyTypes.contains(propertyType));
//				System.out.println(propertyTypes.indexOf(propertyType));
				listOfProperties.add(property);
				System.out.println(property.getClass().getSimpleName());
				
//				System.out.println(fileScanner.hasNextLine());
//				System.out.println(rowScanner.hasNext());
				rowScanner.close();
			}
			
			
			oos.writeObject("List of Properties");
			for (Property rp : listOfProperties) oos.writeObject(rp);
			System.out.println("Serializable file \"Load_Properties.dat\" is created");
		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("ITECH2306_2020_Load_Properties.csv OR Load_RatePayers.dat file cannot be located for opening");
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
