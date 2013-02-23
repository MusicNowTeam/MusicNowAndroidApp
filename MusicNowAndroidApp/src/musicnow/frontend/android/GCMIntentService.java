package musicnow.frontend.android;

import static musicnow.frontend.android.CommonUtilities.SENDER_ID;
import static musicnow.frontend.android.CommonUtilities.displayMessage;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;
import com.shared.app.messages.MusicNowMessage;

public class GCMIntentService extends GCMBaseIntentService {
	
    @SuppressWarnings("hiding")
    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

	
	@Override
	protected void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		System.out.println("onError");
        System.out.println("Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));

	}
	
    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        System.out.println("Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }


	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onMessage");
        System.out.println("Received message. Extras: " + intent.getExtras());
        
        System.out.println("reg id: "+ intent.getExtras().getString(MusicNowMessage.REG_ID));
        
        
        String message = getString(R.string.gcm_message);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);

	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		// TODO Auto-generated method stub
		System.out.println("onRegistered");
		System.out.println("Device registered: regId = " + registrationId);
        displayMessage(context, getString(R.string.gcm_registered,
                registrationId));
        ServerUtilities.register(context, registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		// TODO Auto-generated method stub
		System.out.println("onUnregistered");
        System.out.println("Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);

	}

    @Override
    protected void onDeletedMessages(Context context, int total) {
        System.out.println("Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.ic_stat_gcm;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }
}
