package core.input.keybinds.keyactions;

import core.event.Event;
import core.event.EventArgs.EventArgs;

public abstract class KeyAction<T extends EventArgs>
{
	public Event<T> onPerformed;
	
	public KeyAction()
	{
		onPerformed = Event.CreateEventDispatcher();
	}
	
	public abstract void update();
}
