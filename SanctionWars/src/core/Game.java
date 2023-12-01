package core;

import core.entities.manager.EntityManager;
import core.graphics.window.GameWindowLayer;
import core.graphics.window.Window;
import core.input.KeyboardManager;
import core.staticobjects.Background;
import core.ui.layers.UIFightHUDLayer;
import core.ui.managers.UIManager;

public class Game
{
	public static Game Instance;
	
	public final int TPS = 60;
	
	private final EntityManager entityManager;
	private final UIManager uiManager;
	private final Background background;
	
	private final boolean running;
	
	public Game(String title, int width, int height)
	{
		Instance = this;
		
		new Window(new GameWindowLayer(), title, width, height);
		
		entityManager = new EntityManager();
		uiManager = new UIManager();
		
		uiManager.addChildElement(new UIFightHUDLayer());
		
		background = new Background("Backgrounds/MajesticMountains-BG.png");
		
		running = true;
	}
	
	public void run()
	{
		double timePerTick = Math.pow(10, 9) / TPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now = 0;
		
		while (running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1)
			{
				KeyboardManager.Update();
				entityManager.update();
				
				delta--;
			}
		}
	}
	
	public EntityManager getEntityManager()
	{
		return entityManager;
	}
	
	public UIManager getUIManager()
	{
		return uiManager;
	}
	
	public Background getBackground()
	{
		return background;
	}
}
