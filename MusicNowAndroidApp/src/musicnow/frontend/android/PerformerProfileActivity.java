package musicnow.frontend.android;

import musicnow.backend.Option;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class PerformerProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_performer_profile);

		
		/*
		 * 	public Boolean updatePerformerAccount(String username, String password,
			String emailAddress, Option genre, byte[] image,
			String description, Integer event, String website) throws Exception {
		 */
		
		
		
		/*-----------------------------------------------------------------
		 * 
		 *    Bottom Button Bar Page Switching Function
		 * 
		 *-----------------------------------------------------------------*/
		
		Button logIn = (Button)findViewById(R.id.login);
    	logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
		});
    	
		Button homePage = (Button) findViewById(R.id.home_page);
    	homePage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
		});
	}
}
