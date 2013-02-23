package musicnow.backend;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import musicnow.frontend.android.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tools.ant.taskdefs.email.EmailAddress;

import android.os.AsyncTask;

import com.google.appengine.api.images.Image;
import com.shared.app.messages.AuthenticateUserLoginRequest;
import com.shared.app.messages.Response;
import com.shared.app.messages.RetrieveAccountResponse;
import com.shared.app.messages.UpdatePerformerAccountRequest;

public class DatabaseManager {

	private static final String BASE_URL = "http://music-now.appspot.com/";
	private static final String APP = "musicnow";

	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	private static class InstanceHolder {
		private static DatabaseManager theInstance = new DatabaseManager();
	}

	public static DatabaseManager getInstance() {
		return InstanceHolder.theInstance;
	}

	private DatabaseManager() {

	}

	List<Option> getMusicGenreOptions() {
		List<Option> list = new ArrayList<Option>();

		// retrieve all music genre options from database

		return list;
	}

	List<Option> getRadiusOptions() {
		List<Option> list = new ArrayList<Option>();

		// retrieve all radius options from database

		return list;
	}

	List<Option> getAccountTypeOptions() {
		List<Option> list = new ArrayList<Option>();

		// retrieve all account type options from database

		return list;
	}

	public Account authenticateUserLogin(final String username,
			final String password) throws Exception {
		// create message
		AuthenticateUserLoginRequest rqst = new AuthenticateUserLoginRequest(
				MainActivity.getRegistrationID(), username, password);

		AsyncTask<AuthenticateUserLoginRequest, Object, RetrieveAccountResponse> task = new AsyncTask<AuthenticateUserLoginRequest, Object, RetrieveAccountResponse>() {

			@Override
			protected RetrieveAccountResponse doInBackground(
					AuthenticateUserLoginRequest... params) {
				try {
					// create a request to connect to the Google App Engine
					HttpPost requestMsg = new HttpPost(BASE_URL + APP);

					// add the data
					requestMsg.setEntity(params[0].getEntity());

					System.out.println("authenticateUserLogin : username: "
							+ username + " password: " + password);
					HttpResponse response = httpClient.execute(requestMsg);

//					InputStreamReader is = new InputStreamReader(response
//							.getEntity().getContent());
//					StringBuilder sb = new StringBuilder();
//					BufferedReader br = new BufferedReader(is);
//					String read = br.readLine();
//					while (read != null) {
//						sb.append(read);
//						read = br.readLine();
//					}
//					System.out.println("response = " + sb.toString());
					RetrieveAccountResponse rsp = new RetrieveAccountResponse(
							response);
					return rsp;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

		}.execute(rqst);

		RetrieveAccountResponse response = task.get();
		if (response != null) {
			if (response.getAccountType().equals("Performer")) {
				System.out.println("Performer account login");
				PerformerAccount account = new PerformerAccount(response.getUsername(), response.getPassword(), response.getEmail(), response.getImage(), response.getDescription(), response.getEvent(), response.getWebsite(), new Option(response.getGenre(), "stuff"));
				return account;
			} else if (response.getAccountType().equals("Venue")) {
				System.out.println("Venue account login");
				VenueAccount account = new VenueAccount(response.getUsername(), response.getPassword(), response.getEmail(), response.getDescription(), response.getImage(), response.getWebsite());
				return account;
			}
		}

		return null;
	}

	public void retrieveUserPassword(String emailAddress) throws Exception {
		
	}

	public Boolean updatePerformerAccount(String username, String password,
			String emailAddress, Option genre, byte[] image,
			String description, Integer event, String website) throws Exception {

		// create message
		UpdatePerformerAccountRequest rqst = new UpdatePerformerAccountRequest(
				MainActivity.getRegistrationID(), username, password,
				emailAddress, description, image, event, website,
				genre.getIndex());

		AsyncTask<UpdatePerformerAccountRequest, Object, Boolean> task = new AsyncTask<UpdatePerformerAccountRequest, Object, Boolean>() {

			@Override
			protected Boolean doInBackground(
					UpdatePerformerAccountRequest... params) {
				try {
					// create a request to connect to the Google App Engine
					HttpPost requestMsg = new HttpPost(BASE_URL + APP);

					// add the data
					requestMsg.setEntity(params[0].getEntity());

					System.out.println("updatePerformerAccount");
					HttpResponse response = httpClient.execute(requestMsg);

//					InputStreamReader is = new InputStreamReader(response
//							.getEntity().getContent());
//					StringBuilder sb = new StringBuilder();
//					BufferedReader br = new BufferedReader(is);
//					String read = br.readLine();
//					while (read != null) {
//						sb.append(read);
//						read = br.readLine();
//					}
//					System.out.println("response = " + sb.toString());

					Response rsp = new Response(response);
					return rsp.getStatus();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Boolean.FALSE;
			}

		}.execute(rqst);

		return task.get();
	}

	public void updateVenueAccount(String username, String password,
			EmailAddress emailAddress, Image image, String description,
			List<Event> events, URL website) {
	}

	public void updateEvent(Account performer, Account venue, Date date,
			Time time) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void retrieveEvent(String referenceLink) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void deleteEvent(String referenceLink) throws Exception {
		throw new Exception("not implemented yet");
	}

	public Account retrieveAccountProfileEdit(String username, String password)
			throws Exception {
		// retrieve account from database
		return new Account(username, password, null, null, null, null);
	}

	public Account retrieveAccountProfileView(String name) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByGPS(Object object, Object object2) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByGPS(Object object, Object object2, Object object3,
			Object object4, Object object5, Object object6) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByCityState(Object object, Object object2)
			throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByCityState(Object object, Object object2,
			Object object3, Object object4, Object object5, Object object6)
			throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByZipCode(Object object) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByZipCode(Object object, Object object2,
			Object object3, Object object4, Object object5) throws Exception {
		throw new Exception("not implemented yet");
	}
}
