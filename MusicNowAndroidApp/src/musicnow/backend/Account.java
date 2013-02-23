package musicnow.backend;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.apache.tools.ant.taskdefs.email.EmailAddress;

import com.google.appengine.api.images.Image;

public class Account {

	private String username = null;
	private String password = null;
	private String email = null;
	private String description = null;
	private byte[] image = null;
	private Integer events = null;
	private String website = null;

	protected Account(String username, String password, String email, String description,
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

}
