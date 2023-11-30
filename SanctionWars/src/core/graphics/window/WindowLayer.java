package core.graphics.window;

import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JPanel;

import core.math.Vec2;

public class WindowLayer extends JPanel implements Runnable
{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 761745203709562311L;
	
	protected Vec2 screenSize = Vec2.Zero();
	
	@Override
	public void paintComponent(Graphics screenGraphics)
	{
		super.paintComponent(screenGraphics);
		
		onRender(screenGraphics);
	}
	
	public void render()
	{
		super.repaint();
	}
	
	public void onRender(Graphics screenGraphics)
	{
	}
	
	public void setScreenSize(Vec2 screenSize)
	{
		this.screenSize = screenSize;
	}
	
	@Override
	public void run()
	{
	
	}
}
