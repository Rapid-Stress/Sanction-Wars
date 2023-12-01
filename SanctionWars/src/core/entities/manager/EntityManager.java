package core.entities.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.entities.*;
import core.input.keybinds.Player.PlayerKeybinds;
import core.input.keybinds.Player.PlayerOneKeybinds;
import core.input.keybinds.Player.PlayerTwoKeybinds;
import core.entities.players.*;
import core.math.Rect;

public class EntityManager 
{
	private static final float ENTITY_SIZE = 400;
	private static final Rect PLAYER_ONE_BOUNDS = new Rect(800, 670, ENTITY_SIZE, ENTITY_SIZE);
	private static final Rect PLAYER_TWO_BOUNDS = new Rect(1120, 670, ENTITY_SIZE, ENTITY_SIZE);
	
	private static final PlayerKeybinds PLAYER_ONE_KEYBINDS = new PlayerOneKeybinds();
	private static final PlayerKeybinds PLAYER_TWO_KEYBINDS = new PlayerTwoKeybinds();
	
	private final List<Entity> entities;
	
	public EntityManager()
	{
		entities = Collections.synchronizedList(new ArrayList<>());
		
		entities.add(new Alisha(PLAYER_ONE_KEYBINDS, PLAYER_ONE_BOUNDS, 5, false));
		entities.add(new Alisha(PLAYER_TWO_KEYBINDS, PLAYER_TWO_BOUNDS, 5, true));
	}

	public void update()
	{
		for (Entity entity : entities)
			entity.update();
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
}
