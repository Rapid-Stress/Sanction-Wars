package core.graphics.window;

import java.awt.*;

import javax.swing.JFrame;

import core.input.KeyboardManager;
import core.input.MouseManager;
import core.math.Vec2;

public class Window implements Runnable
{
	public static Window Instance;
	
	public static final int RENDER_WIDTH = 1920;
	public static final int RENDER_HEIGHT = 1080;
	public static final int FPS = 60;
	
	private final JFrame jframe;
	private final WindowLayer layer;
	
	private int width;
	private int height;
	private boolean running = false;
	
	public Window(WindowLayer layer, String title, int width, int height)
	{
		Instance = this;
		
		this.width = width;
		this.height = height;
		this.layer = layer;
		
		Thread renderThread = new Thread(this);
		jframe = new JFrame(title);
		
		init();
		
		renderThread.start();
		running = true;
	}
	
	private void init()
	{
		jframe.setSize(width, height);
		jframe.setVisible(true);
		jframe.setPreferredSize(new Dimension(width, height));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(layer);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.pack();
		
		layer.setFocusable(true);
		layer.requestFocusInWindow();
		
		layer.addKeyListener(KeyboardManager.Instance);
		layer.addMouseListener(MouseManager.Instance);
		layer.addMouseMotionListener(MouseManager.Instance);
		
		layer.setScreenSize(new Vec2(width, height));
	}
	
	private void render()
	{
		layer.render();
		
		width = jframe.getWidth();
		height = jframe.getHeight();
		layer.setScreenSize(new Vec2(width, height));
	}
	
	public void setResizable(boolean resizable)
	{
		jframe.setResizable(resizable);
	}
	
	public int getScreenWidth()
	{
		return width;
	}
	
	public int getScreenHeight()
	{
		return height;
	}
	
	@Override
	public void run()
	{
		double timePerFrame = Math.pow(10, 9) / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now = 0;
		
		while (running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerFrame;
			lastTime = now;
			
			if (delta >= 1)
			{
				render();
				
				delta--;
			}
		}
	}
}
