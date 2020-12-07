import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar AntennaTest.java
// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AntennaTest
public class AntennaTest {
	private AntennaImpl antenna;
	private EspressOSMobile osmobile;
	@Before
	public void setup(){
		antenna = new AntennaImpl();
		osmobile = new EspressOSMobile();
	}
	
	@Test
	public void testIsConnected(){
		assertEquals(antenna.isConnected(),false);
	}
	
	@Test
	public void testSetNetwork(){
		assertEquals(antenna.isConnected(),false);
		antenna.setNetwork(true);
		
		assertEquals(antenna.isConnected(),true);
	}
	
	@Test
	public void testGetSignalStrength(){
		assertEquals(antenna.getSignalStrength(),0);
	}
	
	@Test
	public void testSetSignalStrength(){
		assertEquals(antenna.getSignalStrength(),0);
		antenna.setSignalStrength(2);
		
		assertEquals(antenna.getSignalStrength(),2);
	}
	
	@Test
	public void testChangeAntenna(){ // using generic in OSMobile changeAntennamethod
		assertEquals(osmobile.changeAntenna(null),false);
		
		antenna.setSignalStrength(-1);
		assertEquals(osmobile.changeAntenna(antenna),false);
		
		antenna.setSignalStrength(6);
		assertEquals(osmobile.changeAntenna(antenna),false);
		
		antenna.setSignalStrength(3);
		assertEquals(osmobile.changeAntenna(antenna),false); //not start the phone.
		
		osmobile.phoneStats = true;
		osmobile.networkstats = false;
		osmobile.batteryLife = 1;
		assertEquals(osmobile.changeAntenna(antenna),false);
		
		osmobile.batteryLife = 10;
		assertEquals(osmobile.changeAntenna(antenna),true);
		assertEquals(osmobile.signal,3);
		assertEquals(osmobile.networkstats,true);
		assertEquals(osmobile.batteryLife,8);
		
		
		osmobile.batteryLife = 2;
		osmobile.networkstats = false;
		osmobile.phoneStats = true;  // reset
		assertEquals(osmobile.changeAntenna(antenna),true);
		assertEquals(osmobile.signal,3);
		assertEquals(osmobile.networkstats,false);
		assertEquals(osmobile.batteryLife,0);
		assertEquals(osmobile.phoneStats,false);
		
		
		osmobile.networkstats = true;
		osmobile.phoneStats = true; // reset
		assertEquals(osmobile.changeAntenna(antenna),true);
		assertEquals(osmobile.signal,3);
		antenna.setSignalStrength(0);
		assertEquals(osmobile.changeAntenna(antenna),true);
		assertEquals(osmobile.signal,0);
		assertEquals(osmobile.networkstats,false);

	}

}
