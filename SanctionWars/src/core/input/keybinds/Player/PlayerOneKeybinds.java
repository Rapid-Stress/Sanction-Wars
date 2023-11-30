package core.input.keybinds.Player;

import core.input.keybinds.keyactions.KeyActionChainTrigger;
import core.input.keybinds.keyactions.KeyActionDirection;

import java.awt.event.KeyEvent;

public class PlayerOneKeybinds extends PlayerKeybinds
{
	public PlayerOneKeybinds()
	{
		attackAction = new KeyActionChainTrigger(new int[] {KeyEvent.VK_J}, KEY_DURATION);
		moveAction = new KeyActionDirection(KeyEvent.VK_D, KeyEvent.VK_A);
		
		apply();
	}
}
