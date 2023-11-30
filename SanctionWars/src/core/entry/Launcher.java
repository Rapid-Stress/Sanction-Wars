package core.entry;

import core.Game;
import editor.Editor;

public class Launcher 
{
	public static final boolean EDITOR_MODE = false;
	
	public static final String TITLE = "Sanction Wars";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static void main(String[] args)
	{
		if (EDITOR_MODE)
		{
			Editor editor = new Editor(WIDTH, HEIGHT);
			editor.run();
		}
		else
		{
			Game game = new Game(TITLE, WIDTH, HEIGHT);
			game.run();
		}
	}
}
