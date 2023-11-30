package core.input.keybinds;

import core.input.keybinds.keyactions.KeyAction;

import java.util.ArrayList;

public abstract class Keybinds
{
	protected ArrayList<KeyAction> keyActions;
	
	public Keybinds()
	{
		keyActions = new ArrayList<>();
	}
	
	public void addAction(KeyAction keyAction)
	{
		this.keyActions.add(keyAction);
	}

	public void update()
	{
		for (KeyAction keyAction : keyActions)
			keyAction.update();
	}
}
