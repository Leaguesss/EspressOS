public class BatteryImpl extends Battery {
	public int batteryLevel;
	
	public BatteryImpl() {
		batteryLevel = -1;
	}
	public void setLevel(int value) {
		batteryLevel = value;
	}
	
	public int getLevel() {
		return batteryLevel;
	}
}
