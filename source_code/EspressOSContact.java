/**
 * EspressOS Mobile Phone Contact Class.
 *
 * EspressOSContact
 * 
 * == Contact data ==
 * Each EspressOSContact stores the first name, last name and phone number of a person. 
 * These can be queried by calling the appropriate get method. They are updated 
 * with new values. The phone number can only be a 6 - 14 digit number.
 * The chat history is also stored. 
 * 
 * 
 * == Chat history ==
 * Each EspressOSContact stores the history of chat messages related to this contact. 
 * Suppose there is a conversation between Angus and Beatrice:
 * 
 * Angus: Man, I'm so hungry! Can you buy me a burrito?
 * Beatrice: I don't have any money to buy you a burrito.
 * Angus: Please? I haven't eaten anything all day.
 * 
 * Each time a message is added the name of the person and the message is 
 * combined as above and recorded in the sequence it was received.
 * 
 * The messages are stored in the instance variable String array chatHistory. Provided for you.
 * Unfortunately there are only 20 messages maximum to store and no more. 
 * When there are more than 20 messages, oldest messages from this array are discarded and 
 * only the most recent 20 messages remain. 
 * 
 * The functions for chat history are 
 *   addChatMessage
 *   getLastMessage
 *   getOldestMessage
 *   clearChatHistory()
 *
 * Using the above conversation as an example
 *   addChatMessage("Angus", "Man, I'm so hungry! Can you buy me a burrito?");
 *   addChatMessage("Beatrice", "I don't have any money to buy you a burrito.");
 *   addChatMessage("Angus", "Please? I haven't eaten anything all day.");
 *
 *   getLastMessage() returns "Angus: Please? I haven't eaten anything all day."
 *   getOldestMessage() returns "Angus: Man, I'm so hungry! Can you buy me a burrito?"
 *
 *   clearChatHistory()
 *   getLastMessage() returns null
 *   getOldestMessage() returns null
 *
 *
 * == Copy of contact ==
 * It is necessary to make copy of this object that contains exactly the same data. 
 * There are many hackers working in other parts of EspressOS, so we cannot trust them 
 * changing the data. A copy will have all the private data and chat history included.
 *
 *
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 *
 */
public class EspressOSContact
{
	public static final int MAXIMUM_CHAT_HISTORY = 20;	
	protected String fname;
	protected String lname;
	protected String pnumber;
	protected int chatHistoryIndex;
	protected int ifclean;
	/* given */
	protected String[] chatHistory;
	
	
	public EspressOSContact(String fname , String lname , String pnumber ) {
		/* given */
		chatHistory = new String[MAXIMUM_CHAT_HISTORY];
		this.fname = fname;
		this.lname = lname;
		this.pnumber = pnumber;
		this.chatHistoryIndex = 1;
		chatHistory[0] = "EspressOS: Thank you for choosing EspressOS products";
		this.ifclean = 1;
	}
	
	public String getFirstName() {
		return this.fname;
	}
	public String getLastName() {
		return this.lname;
	}
	public String getPhoneNumber() {
		return this.pnumber;
	}

	/* if firstName is null the method will do nothing and return
	 */
	public void updateFirstName(String firstName) {
		if (firstName == null) {
			return;
		}
		this.fname = firstName;
	}
	/* if lastName is null the method will do nothing and return
	 */
	public void updateLastName(String lastName) {
		if (lastName == null) {
			return;
		}
		this.lname = lastName;
	}
	
	/* only allows integer numbers (long type) between 6 and 14 digits
	 * no spaces allowed, or prefixes of + symbols
	 * leading 0 digits are allowed
	 * return true if successfully updated
	 * if number is null, number is set to an empty string and the method returns false
	 */
	public boolean updatePhoneNumber(String number) {
		try{
			long s = Long.parseLong(number);
		} catch(NumberFormatException e) {
			return false;
		}
		if(number == null) {
			number = "";
			return false;
		}
		if (number.length() < 6 || number.length() > 14) {
			return false;
		}
		this.pnumber = number;
		return true;
	}
	
	/* add a new message to the chat
	 * The message will take the form
	 * whoSaidIt + ": " + message
	 * 
	 * if the history is full, the oldest message is replaced
	 * Hint: keep track of the position of the oldest or newest message!
	 */
	public void addChatMessage(String whoSaidIt, String message) {
		if(chatHistoryIndex > 19) {
			chatHistory[chatHistoryIndex % 20] = whoSaidIt + ": " + message;	
		} else {
			chatHistory[chatHistoryIndex] = whoSaidIt + ": " + message;
		}
		chatHistoryIndex += 1;
	}
	

	/* after this, both last and oldest message should be referring to index 0
	 * all entries of chatHistory are set to null
	 */
	public void clearChatHistory() {
		for(int i = 0; i < this.chatHistory.length; i++) {
			chatHistory[i] = null;
		}
		this.chatHistoryIndex = 0;
		this.ifclean = 0;
	}

	/* returns the last message 
	 * if no messages, returns null
	 */
	public String getLastMessage() {
		if (chatHistoryIndex > 1) {
			return chatHistory[(chatHistoryIndex-1) % 20];
		} else {
			return null;
		}

	}
	
	/* returns the oldest message in the chat history
	 * depending on if there was ever MAXIMUM_CHAT_HISTORY messages
	 * 1) less than MAXIMUM_CHAT_HISTORY, returns the first message
	 * 2) more than MAXIMUM_CHAT_HISTORY, returns the oldest
	 * returns null if no messages exist
	 */
	public String getOldestMessage() {
		if (chatHistoryIndex == 1) {
			return null;
		}
		if(chatHistoryIndex >= MAXIMUM_CHAT_HISTORY) {
			return chatHistory[chatHistoryIndex % 20];
		} else {
			return chatHistory[ifclean];
		}
	}


	/* creates a copy of this contact
	 * returns a new EspressOSContact object with all data same as the current object
	 */
	
	public EspressOSContact copy() {
		EspressOSContact s = new EspressOSContact(fname,lname,pnumber);
		for(int i =0;i<s.chatHistory.length;i++) {
			s.chatHistory[i] = this.chatHistory[i];
		}
		return s;
	
	}
	
	/* -- NOT TESTED --
	 * You can impelement this to help with debugging when failing ed tests 
	 * involving chat history. You can print whatever you like
	 * Implementers notes: the format is printf("%d %s\n", index, line); 
	 */
	public void printMessagesOldestToNewest() {
		System.out.printf("%s %s\n", getOldestMessage(), getLastMessage());
	}
}
