/**
 * 
 */
package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import domain.*;

/**
 * @author Zac
 * @author Anush
 *
 */
public class LoadProperties {

	private static final int RESIDENTIAL = 0;
	private static final int COMMERCIAL = 1;
	private static final int VACANT_LAND = 2;
	private static final int HOSPITAL = 3;
	private static final int INDUSTRIAL = 4;
	private static final int SCHOOL_COMMUNITY = 5;
	private static final int OTHER = 6;
	private static final String TRUE = "TRUE";
	private static final String LOAD_PROPERTIES_DAT = "files/Load_Properties.dat";
	private static final String LOAD_PROPERTIES_CSV = "files/ITECH2306_2020_Load_Properties.csv";
	private static final ArrayList<String> PROPERTY_TYPE_NAMES = new ArrayList<String>(Arrays.asList("Residential", "Commercial", "VacantLand", 
																				"Hospital", "Industrial", "SchoolCommunity", "Other"));
	private ArrayList<Property> listOfProperties = new ArrayList<Property>();
	
	public LoadProperties() {
	}

	public ArrayList<Property> getListOfProperties() {
		return listOfProperties;
	}

	private void setListOfProperties(ArrayList<RatePayer> listOfRatePayers) {
		
		String propertyType = null;
		String description = null;
		String location = null;
		double area = 0;
		double siteValue = 0;
		double capitalImprovedValue = 0;
//		double capitalImprovedRate;
		double netAnnualValue = 0;
		String valuationDate = null;
		RatePayer owner = null;
		String extraAttr1 = null;
		String extraAttr2= null;
		int extraAttr3 = 0;
		boolean extraBooleanAttr = false;
		int column = 0;
		
		System.out.println("Setting up list of Properties...");
		
		try (Scanner fileScanner = new Scanner(new File(LOAD_PROPERTIES_CSV));
				FileOutputStream fos = new FileOutputStream(new File(LOAD_PROPERTIES_DAT));
				ObjectOutputStream oos = new ObjectOutputStream(fos);) 
		{	
			System.out.println("\"ITECH2306_2020_Load_Properties.csv\" file is located \n");				
						
			while (fileScanner.hasNextLine()) {
				Scanner rowScanner = new Scanner(fileScanner.nextLine());
				rowScanner.useDelimiter(",");
				String stringData = null;
				double doubleData = 0;
				while (rowScanner.hasNext()) {
					
					stringData = rowScanner.next();
					stringData = stringData.trim();
					
					if ((column >= 3 && column <= 7) || column == 12) {
						doubleData = Double.parseDouble(stringData);
					}
			
					switch (column) {
					case 0:
						propertyType = stringData;
						break;
					case 1:
						description = stringData;
						break;
					case 2:
						location = stringData;
						break;
					case 3:
						area = doubleData;
						break;
					case 4:
						siteValue = doubleData;
						break;
					case 5:
						capitalImprovedValue = doubleData;
						break;
					case 6:
//						capitalImprovedRate = doubleData; 
						break;
					case 7:
						netAnnualValue = doubleData;
						break;
					case 8:
						valuationDate = stringData;
						break;
					case 9:
						for (RatePayer payer: listOfRatePayers) {
							if (payer.getName().equalsIgnoreCase(stringData)) {
								owner = payer;
								break;
							}
						}
						break;
					case 10:
						if (propertyType.equalsIgnoreCase(PROPERTY_TYPE_NAMES.get(3))) { 
							extraBooleanAttr = (stringData.equalsIgnoreCase(TRUE))? true : false;
						}
						else {
							extraAttr1 = stringData ;
						}
						break;
					case 11:
						if (propertyType.equalsIgnoreCase(PROPERTY_TYPE_NAMES.get(4))) {
							extraBooleanAttr = (stringData.equalsIgnoreCase(TRUE))? true : false;
						}
						else {
							extraAttr2 = stringData ;
						}
						break;
					case 12:
						extraAttr3 =(int) doubleData;
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
				if (PROPERTY_TYPE_NAMES.contains(propertyType)) {
					int propertyTypeIndex = PROPERTY_TYPE_NAMES.indexOf(propertyType);
					switch (propertyTypeIndex) {
					case(RESIDENTIAL):
						property = new Residential(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
						break;
					case(COMMERCIAL):
						property = new Commercial(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
						break;
					case(VACANT_LAND):
						property = new VacantLand();
						break;
					case(HOSPITAL):
						property = new Hospital(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraBooleanAttr, extraAttr2, extraAttr3);
						break;
					case(INDUSTRIAL):
						property = new Industrial(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraBooleanAttr);
						break;
					case(SCHOOL_COMMUNITY):
						property = new SchoolCommunity(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1, extraAttr2);
						break;
					case(OTHER):
						property = new OtherProperty(description, location, area, siteValue, capitalImprovedValue, netAnnualValue, valuationDate, owner, extraAttr1);
						break;	
					}
				}
				this.listOfProperties.add(property);				
				System.out.println(property);
				rowScanner.close();
			}
			oos.writeObject("List of Properties");
			for (Property p : listOfProperties) { 
				oos.writeObject(p);
			}
			System.out.println("Number of Properties: " + listOfProperties.size() + "\n");
			System.out.println("Serializable file \"Load_Properties.dat\" is created");
		}
		catch(FileNotFoundException fnfExc) {
			System.out.println("Unable to locate file for opening: " + fnfExc.getMessage());
			fnfExc.printStackTrace();
		}
		catch(IOException ioExc) {
			System.out.println("Problem with file processing: " + ioExc.getMessage());
			ioExc.printStackTrace();
		}
		catch (NullPointerException npExc) {
			System.out.println(npExc.getMessage());
		}
		catch (NumberFormatException nfExc) {
			System.out.println("Unable to convert string to double: " + nfExc.getMessage());
			nfExc.printStackTrace();
		}
		catch(Exception otherExc) {
			System.out.println("Something went wrong: " + otherExc.getMessage());
			otherExc.printStackTrace();
		}
	}
	
	public void loadListOfProperties() {
		
		System.out.println("Loading list of Properties...");
		
		try(FileInputStream fis = new FileInputStream(LOAD_PROPERTIES_DAT); 
			ObjectInputStream ois = new ObjectInputStream(fis);) 
		{
			System.out.println("\"Load_Properties.dat\" file is located");
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
				if (nextThing instanceof Property) {
//					System.out.println("Next thing is a Property");
					Property property = (Property) nextThing;
					listOfProperties.add(property);
				}
				else {
					// Throw some exceptions here
					System.out.println("Next thing is not a Property: " + nextThing);
				}
			}
			System.out.println("Number of Properties: " + listOfProperties.size() + "\n");
//			this.listOfProperties = list;
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
			LoadProperties loadProperties = new LoadProperties();
			LoadRatePayers loadRatePayers = new LoadRatePayers();
			loadRatePayers.loadListOfRatePayers();
			loadProperties.setListOfProperties(loadRatePayers.getListOfRatePayers());
	}
	
	
}
