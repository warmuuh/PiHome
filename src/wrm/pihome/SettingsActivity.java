package wrm.pihome;

import android.os.Bundle;
import roboguice.activity.RoboFragmentActivity;

public class SettingsActivity extends RoboFragmentActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		  getFragmentManager().beginTransaction()
          .replace(android.R.id.content, new SettingsFragment())
          .commit();
	}

}
