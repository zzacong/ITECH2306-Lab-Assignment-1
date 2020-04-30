/**
 * 
 */
package domain;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * @author Zac
 *
 */
public class PropertyTypeTestCaseRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SchoolCommunityTestCase.class, CommercialTestCase.class);
		System.out.println(result.getFailures());
	}

}
