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
	
	public static void main(String[] args) {
		
		ArrayList<String> propertyTypes = new ArrayList<String>();
//		propertyTypes = {RESIDENTIAL, COMMERCIAL, VACANTLAND, HOSPITAL, INDUSTRIAL, SCHOOL_COMMUNITY, OTHER};
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
		double area;
		double siteValue;
		double capitalImprovedValue;
		double capitalImprovedRate;
		double netAnnualValue;
		String valuationDate = null;
		String owner = null;
		String additionalAttr1 = null;
		String additionalAttr2= null;
		String additionalAttr3= null;
		
		ArrayList<Property> listOfProperties = new ArrayList<Property>();
		
		Scanner fileScanner = null;
		int column = 0;
		
		try {
			File file = new File("files/ITECH2306_2020_Load_Properties.csv");
			fileScanner = new Scanner(file);
			System.out.println("File is located");
			
			while (fileScanner.hasNextLine()) {
				
				Scanner rowScanner = new Scanner(fileScanner.nextLine());
				rowScanner.useDelimiter(",");
				
				while (rowScanner.hasNext()) {
					String dataStr;
					Double dataDbl;
					switch (column) {
					case 0:
						dataStr = rowScanner.next();
						propertyType = dataStr;
						break;
					case 1:
						dataStr = rowScanner.next();
						description = dataStr;
						break;
					case 2:
						dataStr = rowScanner.next();
						location = dataStr;
						break;
					case 3:
						dataDbl = rowScanner.nextDouble();
						area = dataDbl;
						break;
					case 4:
						dataDbl = rowScanner.nextDouble();
						siteValue = dataDbl;
						break;
					case 5:
						dataDbl = rowScanner.nextDouble();
						capitalImprovedValue = dataDbl;
						break;
					case 6:
						dataDbl = rowScanner.nextDouble();
						capitalImprovedRate = dataDbl; 
						break;
					case 7:
						dataDbl = rowScanner.nextDouble();
						netAnnualValue = dataDbl;
						break;
					case 8:
						dataStr = rowScanner.next();
						valuationDate = dataStr;
						break;
					case 9:
						dataStr = rowScanner.next();
						owner = dataStr;
						break;
					case 10:
						dataStr = rowScanner.next();
						additionalAttr1 = dataStr;
						break;
					case 11:
						dataStr = rowScanner.next();
						additionalAttr2 = dataStr;
						break;
					case 12:
						dataStr = rowScanner.next();
						additionalAttr3 = dataStr;
						break;
					}
					if (!rowScanner.hasNext()) column = 0; 
					else column++;
				}
				
				Property property = null;
				if (propertyTypes.contains(propertyType)) {
					switch (propertyTypes.indexOf(propertyType)) {
					case(RESIDENTIAL):
						property = new Residential();
						System.out.println("Residential property type created");
						break;
					case(COMMERCIAL):
						property = new Commercial();
						System.out.println("Commercial property type created");
						break;
					case(VACANT_LAND):
						property = new VacantLand();
						System.out.println("Vacant Land is not yet implemented");
						break;
					case(HOSPITAL):
						property = new Hospital();
						System.out.println("Hospital property type created");	
						break;
					case(INDUSTRIAL):
						property = new Industrial();
						System.out.println("Industrial property type created");
						break;
					case(SCHOOL_COMMUNITY):
						property = new SchoolCommunity();
						System.out.println("School Communnity property type created");
						break;
					case(OTHER):
						property = new OtherProperty();
						System.out.println("Other property type created");	
						break;	
					}
				}
				System.out.println(propertyType);
//				System.out.println(propertyTypes.contains(propertyType));
//				System.out.println(propertyTypes.indexOf(propertyType));
				listOfProperties.add(property);
//				System.out.println(property);
				rowScanner.close();
			}
			fileScanner.close();
			File binFile = new File("files/Load_Properties.dat");
			FileOutputStream fos = new FileOutputStream(binFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject("List of Properties");
			for (Property rp : listOfProperties) oos.writeObject(rp);
			
			oos.close();
			fos.close();
			System.out.println("Serializable file is created");
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Unable to locate the ITECH2306_2020_Load_Properties.csv file for opening");
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
