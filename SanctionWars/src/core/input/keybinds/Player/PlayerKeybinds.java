package core.input.keybinds.Player;

import core.input.keybinds.Keybinds;
import core.input.keybinds.keyactions.KeyActionChainTrigger;
import core.input.keybinds.keyactions.KeyActionDirection;

public abstract class PlayerKeybinds extends Keybinds
{
	protected static final int KEY_DURATION = 5;
	
	public KeyActionChainTrigger attackAction;
	public KeyActionDirection moveAction;
	
	public final void apply()
	{
		addAction(attackAction);
		addAction(moveAction);
	}
}
