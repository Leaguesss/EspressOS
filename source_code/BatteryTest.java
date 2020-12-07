import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar BatteryTest.java
// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore BatteryTest


public class BatteryTest {
	private BatteryImpl battery;
	private EspressOSMobile osmobile;
	@Before
	public void setup(){
		battery = new BatteryImpl();
		osmobile = new EspressOSMobile();
	}
	
	@Test
	public void testSetAndGetLevel(){
		
		assertEquals(battery.getLevel(),-1);
		battery.setLevel(3);
		battery.setLevel(5);
		assertEquals(battery.getLevel(),5);
	}
	
	@Test
	public void testchangeBattery(){  // using generic in OSMobile changeBatterymethod
		battery.setLevel(-1);
		assertEquals(osmobile.changeBattery(battery),false);
		
		battery.setLevel(101);
		assertEquals(osmobile.changeBattery(battery),false);
		
		battery.setLevel(70);
		assertEquals(osmobile.changeBattery(battery),true);
		assertEquals(osmobile.phoneStats,false);
		assertEquals(osmobile.batteryLife,70);
	}
	

}
