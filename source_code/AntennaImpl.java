public class AntennaImpl extends Antenna  {
	public int signalStrength;
	public boolean isconnected;
	
	public AntennaImpl() {
		signalStrength = 0;
		isconnected = false;
	}
	
	public boolean isConnected() {
		return isconnected;
	}
	
    public void setNetwork(boolean isConnected) {
		
		isconnected = isConnected;
	}
	
    public int getSignalStrength() {
		return signalStrength;
	}
	
    public void setSignalStrength(int n) {
		
		signalStrength = n;
	}
}
