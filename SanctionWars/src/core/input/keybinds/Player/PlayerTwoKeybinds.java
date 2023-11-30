package core.input.keybinds.Player;

import core.input.keybinds.keyactions.KeyActionChainTrigger;
import core.input.keybinds.keyactions.KeyActionDirection;

import java.awt.event.KeyEvent;

public class PlayerTwoKeybinds extends PlayerKeybinds
{
	public PlayerTwoKeybinds()
	{
		attackAction = new KeyActionChainTrigger(new int[] {KeyEvent.VK_NUMPAD4}, KEY_DURATION);
		moveAction = new KeyActionDirection(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
		
		apply();
	}
}
