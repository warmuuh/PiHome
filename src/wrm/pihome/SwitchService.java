package wrm.pihome;

import javax.inject.Inject;

import roboguice.event.Observes;
import roboguice.inject.ContextSingleton;
import wrm.pihome.events.ReloadPreferencesEvent;
import wrm.pihome.events.SwitchButtonEvent;
import wrm.pihome.promise.Deferred.Callback;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

@ContextSingleton
public class SwitchService {

	@Inject
	Context context;
	
	@Inject
	PiServeApi api;
	
	
	@Inject
	SharedPreferences prefs;
	
	public void onSwitchButtonEvent(@Observes final SwitchButtonEvent event) {
		// @formatter:off
		api.switchPlug(event)
		.then(new Callback<Integer>() { public void call(Integer value) {
			String message = "Switch turned " + (event.isSwitchOn() ? "on" : "off") + ".";
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();	
		}})
		.otherwise(new Callback<Exception>() { public void call(Exception exception) {
			String message = "Failed to switch plug: " + exception.toString();
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();	
		}});
		// @formatter:on
	}
	
	

	public void loadPreferences(@Observes ReloadPreferencesEvent evt) {
		String host = prefs.getString("prefHost", "");
		String user = prefs.getString("prefUser", "");
		String password = prefs.getString("prefPassword", "");
		api.setConfiguration(host, user, password);
	}
	
	
	
}
