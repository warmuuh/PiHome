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

public class SwitchesFragment extends RoboFragment implements  OnCheckedChangeListener {
	
	@InjectView(R.id.cb_switch1)
	CheckBox switch1;
	@InjectView(R.id.cb_switch2)
	CheckBox switch2;
	@InjectView(R.id.cb_switch3)
	CheckBox switch3;
	@InjectView(R.id.cb_switch4)
	CheckBox switch4;
	
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
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this); 
        switch3.setOnCheckedChangeListener(this); 
        switch4.setOnCheckedChangeListener(this); 
        
	}
	
	public void onCheckedChanged(CompoundButton b, boolean isChecked) {
		int id = Integer.parseInt((String)b.getTag());
		events.fire(new SwitchButtonEvent(id, isChecked));
	}
}
