package wrm.pihome;

import javax.inject.Inject;

import android.app.Application;
import android.content.SharedPreferences;
import roboguice.RoboGuice;
import roboguice.event.EventManager;
import roboguice.inject.RoboApplicationProvider;
import wrm.pihome.events.ReloadPreferencesEvent;

public class PiHomeApplication extends Application{

	@Inject
	SwitchService switchService;

	@Inject
	EventManager events;
	
	@Override
	public void onCreate() {
		super.onCreate();
		RoboGuice.injectMembers(this, this);
		events.fire(new ReloadPreferencesEvent());
	}
	
	
}
