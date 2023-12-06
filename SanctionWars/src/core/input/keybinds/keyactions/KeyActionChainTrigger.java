package core.input.keybinds.keyactions;

import core.event.eventargs.EventArgs;
import core.event.EventListener;
import core.input.KeyboardManager;

public class KeyActionChainTrigger extends KeyAction<EventArgs>
{
	private final int keyPressedDuration;
	private final int[] keyPressedStateBuffer;
	
	public KeyActionChainTrigger(int[] keys, int keyPressedDuration)
	{
		this.keyPressedDuration = keyPressedDuration;
		keyPressedStateBuffer = new int[keys.length];
		
		for (int i = 0; i < keys.length; i++)
		{
			final int keyIndex = i;
			KeyboardManager.ListenToKey(keys[i], KeyboardManager.KeyListenType.ON_PRESSED,
										(Object sender, EventListener<EventArgs> listenerInstance, EventArgs eventArgs) ->
										{
											keyPressedStateBuffer[keyIndex] = this.keyPressedDuration;
											return null;
										});
		}
	}
	
	@Override
	public void update()
	{
		boolean performed = true;
		for (int keyPressedState : keyPressedStateBuffer)
		{
			if (keyPressedState <= 0)
			{
				performed = false;
				break;
			}
		}
		
		if (performed)
			onPerformed.dispatch(this, EventArgs.Empty());
		
		for (int i = 0; i < keyPressedStateBuffer.length; i++)
		{
			if (performed)
				keyPressedStateBuffer[i] = 0;
			else
			if (keyPressedStateBuffer[i] > 0)
				keyPressedStateBuffer[i]--;
		}
	}
}
