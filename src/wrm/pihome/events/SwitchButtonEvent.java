package wrm.pihome.events;

public class SwitchButtonEvent {

	final int id;
	final boolean switchOn;
	
	public SwitchButtonEvent(int id, boolean switchOn) {
		this.id = id;
		this.switchOn = switchOn;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isSwitchOn() {
		return switchOn;
	}
}
