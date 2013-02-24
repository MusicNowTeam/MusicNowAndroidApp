package musicnow.frontend.android;

import static musicnow.frontend.android.CommonUtilities.EXTRA_MESSAGE;
import static musicnow.frontend.android.CommonUtilities.SENDER_ID;
import musicnow.backend.DatabaseManager;
import musicnow.backend.Option;
import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.shared.app.messages.MusicNowMessage;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	private static String registrationID = null;
	
    AsyncTask<Void, Void, Void> mRegisterTask;

    public static String getRegistrationID() {
    	return registrationID;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);		
		setContentView(R.layout.activity_main);

		
        registerReceiver(mHandleMessageReceiver,
                new IntentFilter(EXTRA_MESSAGE));
        registrationID = GCMRegistrar.getRegistrationId(this);
        System.out.println("GCM Reg Id is = " + registrationID);
        if (registrationID.equals("")) {
            // Automatically registers application on startup.
        	System.out.println("2 GCM Reg Id is = " + registrationID);
            GCMRegistrar.register(this, SENDER_ID);
            System.out.println("3 GCM Reg Id is = " + registrationID);
        } else {
            // Device is already registered on GCM, check server.
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
            	System.out.println(getString(R.string.already_registered) + "\n");
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        ServerUtilities.register(context, registrationID);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
            }
        }
        
		/*-----------------------------------------------------------------
		 * 
		 *    Bottom Button Bar Page Switching Function
		 * 
		 *-----------------------------------------------------------------*/
		
		Button logIn = (Button) findViewById(R.id.login);
    	logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
		});

		Button accountLogIn = (Button) findViewById(R.id.account_login);
		accountLogIn.setOnClickListener(new View.OnClickListener() {
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
		
		testQuery();
	}

    @Override
    protected void onDestroy() {
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        unregisterReceiver(mHandleMessageReceiver);
        GCMRegistrar.onDestroy(this);
        super.onDestroy();
    }

	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	/*
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());/
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


    private final BroadcastReceiver mHandleMessageReceiver =
            new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	System.out.println("onReceive");
        	
        	String reg_id = intent.getStringExtra(MusicNowMessage.REG_ID);
        	System.out.println("reg id = " + reg_id);
        	
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            System.out.println(newMessage + "\n");
        }
    };

	protected void testQuery() {
		System.out.println("testQuery");
		try {
			DatabaseManager.getInstance().authenticateUserLogin("sam394", "password1");
			byte[] image = new byte[500];
			for(byte a : image) {
				a = Byte.valueOf("8");
			}
			//DatabaseManager.getInstance().updatePerformerAccount("testPerformerUser3", "testPassword2", "musicnow.team2@gmail.com", new Option(1, "indie"), image, "test performer account", 0, "www.google.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
