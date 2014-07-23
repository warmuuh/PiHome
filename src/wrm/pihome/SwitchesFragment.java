package wrm.pihome;

import javax.inject.Inject;

import roboguice.event.EventManager;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import wrm.pihome.events.SwitchButtonEvent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SwitchesFragment extends RoboFragment {
	
	@InjectView(R.id.cb_switch1)
	CheckBox switch1;
	
	@Inject
	EventManager events;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_switches, container, false);
    }
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				events.fire(new SwitchButtonEvent(1, isChecked));
			}
		});
        
	}
}
