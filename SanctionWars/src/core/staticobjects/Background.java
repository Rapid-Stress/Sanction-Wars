package core.staticobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.graphics.rendering.window.Window;
import core.utils.AssetLoader;

public class Background
{
	private final BufferedImage texture;
	
	public Background(String path)
	{
		texture = AssetLoader.LoadTexture(path);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, 0, 0, Window.RENDER_WIDTH, Window.RENDER_HEIGHT, null);
	}
}
