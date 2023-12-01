package editor;

import core.graphics.window.EditorWindowLayer;
import core.graphics.window.Window;
import core.ui.managers.UIManager;

public class Editor
{
	public static Editor Instance;
	
	public final int FPS = 60;
	public boolean running = false;
	
	private final UIManager uiManager;
	
	public Editor(int width, int height)
	{
		Instance = this;
		
		Window window = new Window(new EditorWindowLayer(), "REDPOM ENGINE", width, height);
		window.setResizable(true);
		
		uiManager = new UIManager();
		
		running = true;
	}
	
	public void run()
	{
		while (running)
		{
			uiManager.update();
			
			try
			{
				Thread.sleep((int)(1000.0f / FPS));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public UIManager getUIManager()
	{
		return uiManager;
	}
}
