package musicnow.backend;

import java.net.URL;
import java.util.List;


import org.apache.tools.ant.taskdefs.email.EmailAddress;

import com.google.appengine.api.images.Image;

public class PerformerAccount extends Account {

	private Option genre;

	public PerformerAccount(String username, String password, String email,
			byte[] image, String description,
			Integer event, String website, Option genre) {
		super(username, password, email, description, image, website);

		this.genre = genre;
	}

	public Option getGenre() {
		return this.genre;
	}
}