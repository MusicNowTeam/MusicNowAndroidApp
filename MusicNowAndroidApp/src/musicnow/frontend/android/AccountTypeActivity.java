package musicnow.frontend.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class AccountTypeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_type);

		
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
    	
		
		/*-----------------------------------------------------------------
		 * 
		 *    Page Specific Function
		 *    
		 *    Buttons to select user type to determine which setup page to use
		 * 
		 *-----------------------------------------------------------------*/
		

    		
    		Button createVenueProfilePage = (Button) findViewById(R.id.user_type_venue_input);
    		createVenueProfilePage.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View view) {
    				Intent myIntent = new Intent(view.getContext(), VenueProfileActivity.class);
    				startActivityForResult(myIntent, 0);
    			}
    		});


    		Button createPerformerProfilePage = (Button) findViewById(R.id.user_type_performer_input);
    		createPerformerProfilePage.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View view) {
    				Intent myIntent = new Intent(view.getContext(), PerformerProfileActivity.class);
    				startActivityForResult(myIntent, 0);
    			}
    		});

	}
}
