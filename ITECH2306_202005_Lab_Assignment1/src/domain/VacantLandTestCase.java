
/**
 * @author - Arun
 * 
 */

package domain;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class VacantLandTestCase {
	
	public VacantLand vl;
	
	@Test
	public void TestOverlaysNull() {
		 vl = new VacantLand();
		assertNull(vl.getOverlays());
	}
	
	@Test
	public void TestOverlaysNotNull() {
		String[] ss = {"over1", "over2"} ;
		vl = new VacantLand(ss);
		assertNotNull(vl.getOverlays());
		
		ss = new String[3];
		ss[0] = "Jan";
		ss[1] = "Feb";
		ss[2] = "Jan";
		
		vl = new VacantLand(ss);
		assertNotNull(vl.getOverlays());	
	}

}
