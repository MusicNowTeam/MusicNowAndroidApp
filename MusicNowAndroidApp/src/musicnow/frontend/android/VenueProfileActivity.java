package musicnow.frontend.android;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class VenueProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue_profile);


		EditText yourEditText= (EditText) findViewById(R.id.account_name_input);
		
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(yourEditText, InputMethodManager.SHOW_IMPLICIT);
		
   
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
