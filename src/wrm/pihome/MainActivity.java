package wrm.pihome;

import javax.inject.Inject;

import roboguice.activity.RoboFragmentActivity;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import wrm.pihome.events.SwitchButtonEvent;
import wrm.pihome.promise.Deferred.Callback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboFragmentActivity {

	private static final int RESULT_SETTINGS = 123;
	
	@Inject
	PiServeApi api;
	
	@Inject
	SharedPreferences prefs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadPreferences();
	}
	
	public void onSwitchButtonEvent(@Observes final SwitchButtonEvent event) {
		// @formatter:off
		api.switchPlug(event)
		.then(new Callback<Integer>() { public void call(Integer value) {
			String message = "Switch turned " + (event.isSwitchOn() ? "on" : "off") + ".";
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();	
		}})
		.otherwise(new Callback<Exception>() { public void call(Exception exception) {
			String message = "Failed to switch plug: " + exception.toString();
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();	
		}});
		// @formatter:on
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SettingsActivity.class);
			startActivityForResult(intent, RESULT_SETTINGS);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case RESULT_SETTINGS:
			loadPreferences();
		}

	}

	private void loadPreferences() {
		String host = prefs.getString("prefHost", "");
		String user = prefs.getString("prefUser", "");
		String password = prefs.getString("prefPassword", "");
		api.setConfiguration(host, user, password);
	}

}
