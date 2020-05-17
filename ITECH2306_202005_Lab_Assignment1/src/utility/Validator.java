package utility;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * @author Zac
 *
 */
public class Validator {

	private static final NumberFormat MYFORMAT = NumberFormat.getNumberInstance();
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");


	public static boolean checkIntWithinRange(String description, int inputInt, int min, int max) {
		if (min >= max) {
			System.out.println("Min must be smaller than Max.");
			return false;
		}
		else {
			if (inputInt <= 0) {
				System.out.println("Input number cannot be zero or a negative value.");
				return false;
			}
			if (inputInt < min || inputInt > max) {
				System.out.println("Input number: " + inputInt + " is out of range. \n" +
									description + " must be within (" + min + "-" + max + "). " + 
									"Assigning default value.");
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public static boolean checkDoubleWithinRange(String description, double inputDouble, double min, double max) {
		MYFORMAT.setMinimumFractionDigits(2);
		MYFORMAT.setMaximumFractionDigits(2);
		if (min >= max) {
			System.out.println("Min must be smaller than Max.");
			return false;
		}
		else {
			if (inputDouble <= 0) {
				System.out.println("Input number cannot be zero or a negative value.");
				return false;
			}
			if (inputDouble < min || inputDouble > max) {
				System.out.println("Input number: " + inputDouble + " is out of range. \n" +
									description + " must be within (" + MYFORMAT.format(min) + "-" + MYFORMAT.format(max) + "). " + 
									"Assigning default value.");
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public static boolean validateString(String description, String inputString) {
		if (inputString == null || inputString.isEmpty() || inputString == "") {
			System.out.println("Input string cannot be null or empty." +
								"Invalid " + description + ". Assigning default value.");
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean validateStringToDate(String description, String dateInString) {
		try {
			if (LocalDate.parse(dateInString, FORMATTER) instanceof LocalDate) {
				return true;
			}
			else {
				return false;
			}	
		}
		catch (DateTimeParseException dtpExc) {
			System.out.println("Invalid format of date in String. ERROR: " + dtpExc.getMessage() +
								"\nAssigning default date for " + description + ".");
			return false;
		}
	}
}
