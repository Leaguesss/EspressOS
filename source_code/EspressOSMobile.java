/**
 * EspressOS Mobile Phone Class.
 *
 *
 * EspressOSMobile
 * In this assignment you will be creating an EspressOS Mobile Phone as part of a simulation.
 * The Mobile phone includes several attributes unique to the phone and has simple functionality.
 * You are to complete 2 classes. EspressOSMobile and EspressOSContact
 *
 * The phone has data
 *  Information about the phone state. 
 *    If it is On/Off
 *    Battery level 
 *    If it is connected to network. 
 *    Signal strength when connected to network
 *  Information about the current owner saved as contact information. 
 *    First name
 *    Last name
 *    Phone number
 *  A list of 10 possible contacts.
 *    Each contact stores first name, last name, phone number and chat history up to 20 messages
 *  
 * The phone has functionality
 *  Turning on the phone
 *  Charging the phone. Increase battery level
 *  Change battery (set battery level)
 *  Use phone for k units of battery (decreases battery level by k)
 *  Search/add/remove contacts
 *
 * Attribute features
 *  if the phone is off. It is not connected. 
 *  if the phone is not connected there is no signal strengthBatteryStock
 *  the attribute for battery life has valid range [0,100]. 0 is flat, 100 is full.
 *  the attribute for signal strength has a valid range [0, 5]. 0 is no signal, 5 is best signal.
 * 
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 *
 */
public class EspressOSMobile
{
	public static final int MAXIMUM_CONTACTS = 10;
	protected boolean phoneStats; //phone on or off
	protected int batteryLife;
	protected boolean networkstats;
	protected int signal;
	/* Use this to store contacts. Do not modify. */
	protected EspressOSContact[] contacts;
	


	/* Every phone manufactured has the following attributes
	 * 
	 * the phone is off
	 * the phone has battery life 25
	 * the phone is not connected
	 * the phone has signal strength 0
	 * Each of the contacts stored in the array contacts has a null value
	 * 
	 * the owner first name "EspressOS"
	 * the owner last name is "Incorporated"
	 * the owner phone number is "180076237867"
	 * the owner chat message should have only one message 
	 *         "Thank you for choosing EspressOS products"
	 *
	 */
	public EspressOSMobile()  {
		/* given */
		this.phoneStats = false;
		this.batteryLife = 25;
		this.networkstats = false;
		this.signal = 0;
		contacts = new EspressOSContact[MAXIMUM_CONTACTS];
	}

	/* returns a copy of the owner contact details
	 * return null if the phone is off
	 */
	public EspressOSContact getCopyOfOwnerContact() {
		if (phoneStats == false) {
			return null;
		}
		EspressOSContact defaullowner = new EspressOSContact("EspressOS","Incorporated","180076237867");
		defaullowner.addChatMessage("EspressOS", "Thank you for choosing EspressOS products");
		return defaullowner;
	}


	/* only works if phone is on
	 * will add the contact in the array only if there is space and does not exist
	 * The method will find an element that is null and set it to be the contact
	 */
	public boolean addContact(EspressOSContact contact) {
		if(phoneStats == true) {
			boolean s = false;
			for(int i =0;i<contacts.length;i++) {
				if(contacts[i]== contact) {
					s = true;
				}
			}
			if (s==false) {
				for(int i =0;i<contacts.length;i++) {
					if(contacts[i] == null) {
						contacts[i] = contact;
						return true;
					}
				}
			}
		
		}			

		return false;
	}

	/* only works if phone is on
	 * find the object and set the array element to null
 	 * return true on successful remove
	 */
	public boolean removeContact(EspressOSContact contact) {
		if (this.phoneStats == true){
			for (int i = 0; i < contacts.length; i++) {
				if (contacts[i] == contact) {
					contacts[i] = null;
					return true;
				}
			}
		}
		return false;

	}

	/* only works if phone is on
	 * return the number of contacts, or -1 if phone is off
	 */
	public int getNumberOfContacts() {
		if(phoneStats == false) {
			return -1;
		}else {
			int count = 0;
			for(int i = 0;i<contacts.length;i++) {
				if(contacts[i] != null) {
					count += 1;
				}
			}
			return count;
		}
	}

	/* only works if phone is on
	 * returns all contacts that match firstname OR lastname
	 * if phone is off, or no results, null is returned
	 */
	public EspressOSContact[] searchContact(String name) {
		if(phoneStats == false) {
			return null;
		}else {
			int count = 0;
			for(int i = 0; i< contacts.length;i++) {
				if(contacts[i] != null) {
					if(contacts[i].fname==name || contacts[i].lname==name) {
						count += 1;
					}
				}
			}
			if (count == 0) {
				return null;
			}
			EspressOSContact[] result = new EspressOSContact[count];
			int index = 0;
			for(int i = 0; i< contacts.length;i++) {
				if(contacts[i] != null) {
					if(contacts[i].fname==name || contacts[i].lname==name){
						result[index] = contacts[i];
						index += 1;
					}
				}
			}

			return result;	
		}
	
	}

	/* returns true if phone is on
	 */
	public boolean isPhoneOn() {
		return this.phoneStats;
	}

	/* when phone turns on, it costs 5 battery for startup. network is initially disconnected
	 * when phone turns off it costs 0 battery, network is disconnected
	 * always return true if turning off
	 * return false if do not have enough battery level to turn on
	 * return true otherwise
	 */
	 public boolean setPhoneOn(boolean on) {
		 if(on == true) {
			 if( batteryLife > 5) {
				 batteryLife -= 5;
				 networkstats = false;
				 phoneStats = true;
				 
			 } else {
				 return false;
			 } 
		 }
		 if(on == false) {
			phoneStats = false;
			networkstats = false;
			return true;
		 }
	
		 return true;
		 

	}
	
	/* Return the battery life level. if the phone is off, zero is returned.
	 */
	public int getBatteryLife() {
		if(phoneStats == false) {
			return 0;
		}else  {
			return batteryLife;
		}

	}
	
	/* Change battery of phone.
	 * On success. The phone is off and new battery level adjusted and returns true
	 * If newBatteryLevel is outside manufacturer specification of [0,100], then 
	 * no changes occur and returns false.
	 */
	public <B extends Battery>boolean changeBattery(B battery) {
		if(battery.getLevel() > 100 || battery.getLevel() < 0) {
			return false;
		}
		phoneStats = false;
		batteryLife = battery.getLevel();
		return true;
	}
	
	/* only works if phone is on. 
	 * returns true if the phone is connected to the network
	 */
	public boolean isConnectedNetwork() {
		if(phoneStats == true && networkstats == true) {
			return true;
		}
		return false;
	}
	
	/* only works if phone is on. 
	 * when disconnecting, the signal strength becomes zero
	 */
	public void disconnectNetwork() {
		if(phoneStats == true) {
			networkstats = false;
			signal = 0;
		}

	}
	
	/* only works if phone is on. 
	 * Connect to network
	 * if already connected do nothing
	 * if connecting: 
	 *  1) signal strength is set to 1 if it was 0
	 *  2) signal strength will be the previous value if it is not zero
	 *  3) it will cost 2 battery life to do so
	 * returns the network connected status
	 */
	public boolean connectNetwork() {
		if(phoneStats == true && networkstats == false){
			if(batteryLife < 2) {
				batteryLife = 0;		
				return networkstats;
			} else{
				networkstats = true;
				if(signal == 0) {
					signal = 1;
				}else{
					;
				}
				
				
				batteryLife -= 2;

				if(batteryLife == 0) {
					phoneStats = false;
					networkstats = false;
					
				}
			}

		}
		return networkstats;
	}
	
	/* only works if phone is on. 
	 * returns a value in range [1,5] if connected to network
	 * otherwise returns 0
	 */
	public int getSignalStrength() {
		if(phoneStats == true) {
			if(networkstats == true && signal>=1 && signal<=5) {
				return signal;
			}
		}
		return 0;
	}

	/* only works if phone is on. 
	 * sets the signal strength and may change the network connection status to on or off
	 * signal of 0 disconnects network
	 * signal [1,5] can connect to network if not already connected
	 * if the signal is set outside the range [0,5], nothing will occur and will return false
	 */
	public boolean setSignalStrength(int x) {
		if(phoneStats == true) {
			if(x <0 || x > 5) {
				return false;
			}
			if (x == 0) {
				networkstats = false;
				signal = 0;
				
			}
			if(x >= 1 && x <= 5) {
				signal = x;
				if(networkstats == false) {
					networkstats = true;
				}
			}
			return true;
		}
		return false;
	}
	
	/* changes the antenna object
	 * signal strength is set to default and is not connected to a network
	 * if this constraint is violated then the antenna should not be changed.
	 * return true if antenna is changed.
	 */
	public<A extends Antenna> boolean changeAntenna(A antenna) {
		if(antenna == null) {
			signal = 0;
			networkstats = false;
			return false;
		}
		if(antenna.getSignalStrength()<0 || antenna.getSignalStrength() > 5 || phoneStats == false) {
			return false;
		} else {
			if(networkstats == false && antenna.getSignalStrength()>0) {
				if(batteryLife<2) {
					return false;
				}
				signal = antenna.getSignalStrength();
				networkstats = true;
				batteryLife-=2;
				if(batteryLife ==0) {
					phoneStats = false;
					networkstats = false;
				}
			}
			if(networkstats == true) {
				if(antenna.getSignalStrength()>0) {
					signal = antenna.getSignalStrength();
					
				}else if (antenna.getSignalStrength()==0) {
					signal = antenna.getSignalStrength();
					networkstats = false;
				}
			}
			return true;
		}
	}
	
	/* each charge increases battery by 10
	 * the phone has overcharge protection and cannot exceed 100
	 * returns true if the phone was charged by 10
	 */
	public boolean chargePhone() {
		if((batteryLife+10) > 100) {
			batteryLife = 100;
		} else{
			batteryLife += 10;
			return true;
		}
		return false;

	}
	
	/* Use the phone which costs k units of battery life.
	 * if the activity exceeds the battery life, the battery automatically 
	 * becomes zero and the phone turns off.
	 */
	public void usePhone(int k) {	
		if((batteryLife > k)) {
			batteryLife -= k;
		} else {	
			batteryLife = 0;
			phoneStats = false;
		}
	}
}
