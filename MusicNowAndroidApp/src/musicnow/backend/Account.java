package musicnow.backend;

import java.net.URL;

import com.google.appengine.api.images.Image;

public class Account {

	private String username = null;
	private String password = null;
	private String email = null;
	private String description = null;
	private byte[] image = null;
	private Integer events = null;
	private String website = null;

	public Account(String username, String password, String email, String description,
			byte[] image, String website) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.website = website;
	}

	public void addEvent(Event event) {
		//this.events.add(event);
	}

	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public String getDescription() {
		return this.description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public Integer getEvents() {
		return this.events;
	}

	public String getWebsite() {
		return this.website;
	}


	public boolean setUsername(String userID) {
		if((checkForNull(userID) == false) || (stringTooLong (userID, 30) == false) ||
				(containsSpecialChars(userID) == false)){
					return false;
				}
		else{
			return true;
		}
	}
	
	public boolean setPassword(String password) {
		if((checkForNull(password) == false)){
					return false;
				}
		else{
			return true;
		}
	}

	public boolean setEmail(String eMail) {
		if((checkForNull(eMail) == false) || (stringTooLong (eMail, 30) == false) ||
				(containsAtSign(eMail) == false)){
					return false;
				}
		else{
			return true;
		}
	}

	public boolean setDescription(String intro) {
		if((checkForNull(intro) == false) || (stringTooLong (intro, 200) == false)){
					return false;
				}
		else{
			return true;
		}
	}

	public boolean setImage(byte[] pic) {
		this.image = pic;
		return true;
	}

	public boolean setEvents(Integer eventID) {
		this.events = eventID;
		return true;
	}

	public boolean setWebsite(String uRL) {
		if((checkForNull(uRL) == false) || (stringTooLong (uRL, 50) == false) ||
				(containsAtSign(uRL) == false)){
					return false;
				}
		else{
			return true;
		}
	}

	
	/*-----------------------------------------------------
	 * 
	 * Conduct some error checked before accepting an input
	 * 
	 * Return a true is all is well
	 * 
	 *----------------------------------------------------*/
	
	boolean checkForNull(String text){
	// Check for null
	
		if (text == null) return false;
		else return true;
	}
	
	
	boolean stringTooLong(String text, int count){
	// Check for userID too long
	
		if(text.length() > count) return false;
		else return true;
	}
	
	
	boolean containsSpecialChars(String text){
	// check for special characters
	
		boolean passed = true;
		char[] convertedText = text.toCharArray();
		
		for (int i =0; i < text.length(); i++){
			if (!Character.isLetter(convertedText[i])){
				passed = false;
			}
		}
		return passed;
	}
	
	boolean containsAtSign(String text){
	// check for a @ in the email address
	
		boolean passed = false;
		
		for (int i =0; i < text.length(); i++){
			if (text.charAt(i) == '@'){
				passed = true;
			}
		}
		return passed;
	}
}
