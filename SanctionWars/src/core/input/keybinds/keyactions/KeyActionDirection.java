package core.input.keybinds.keyactions;

import core.event.eventargs.EventArgs;
import core.event.EventListener;
import core.input.KeyboardManager;
import core.event.eventargs.EventArgsDirection;

public class KeyActionDirection extends KeyAction<EventArgsDirection>
{
	private float direction;
	private float previousDirection;
	
	public KeyActionDirection(int positiveKey, int negativeKey)
	{
		KeyboardManager.ListenToKey(positiveKey, KeyboardManager.KeyListenType.ON_HELD,
									(Object sender, EventListener<EventArgs> listenerInstance, EventArgs eventArgs) ->
									{
										direction = 1;
										return null;
									});
		
		KeyboardManager.ListenToKey(positiveKey, KeyboardManager.KeyListenType.ON_RELEASED,
									(Object sender, EventListener<EventArgs> listenerInstance, EventArgs eventArgs) ->
									{
										direction = 0;
										return null;
									});
		
		KeyboardManager.ListenToKey(negativeKey, KeyboardManager.KeyListenType.ON_HELD,
									(Object sender, EventListener<EventArgs> listenerInstance, EventArgs eventArgs) ->
									{
										direction = -1;
										return null;
									});
		
		KeyboardManager.ListenToKey(negativeKey, KeyboardManager.KeyListenType.ON_RELEASED,
									(Object sender, EventListener<EventArgs> listenerInstance, EventArgs eventArgs) ->
									{
										direction = 0;
										return null;
									});
	}
	
	@Override
	public void update()
	{
		if (previousDirection != direction)
		{
			onPerformed.dispatch(this, EventArgsDirection.New(direction));
			previousDirection = direction;
		}
	}
}
