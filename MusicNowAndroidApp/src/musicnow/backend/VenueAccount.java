package musicnow.backend;

import java.net.URL;


import org.apache.tools.ant.taskdefs.email.EmailAddress;

import com.google.appengine.api.images.Image;

public class VenueAccount extends Account {

	protected VenueAccount(String username, String password, String email,
			String description, byte[] image, String website) {
		super(username, password, email, description, image, website);

	}

}