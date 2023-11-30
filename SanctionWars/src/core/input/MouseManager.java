package core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import core.math.Vec2;

public class MouseManager implements MouseListener, MouseMotionListener
{
	public static final MouseManager Instance = new MouseManager();
	
	public enum MouseButton
	{
		LEFT,
		MIDDLE,
		RIGHT
	}
	
	private final Vec2 mousePos;
	
	private boolean leftHeld, middleHeld, rightHeld;
	
	public MouseManager()
	{
		mousePos = Vec2.Zero();
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mousePos.x = e.getX();
		mousePos.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mousePos.x = e.getX();
		mousePos.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{	
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		switch (e.getButton())
		{
			case MouseEvent.BUTTON1:
				leftHeld = true;
				break;
				
			case MouseEvent.BUTTON2:
				middleHeld = true;
				break;
				
			case MouseEvent.BUTTON3:
				rightHeld = true;
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		switch (e.getButton())
		{
			case MouseEvent.BUTTON1:
				leftHeld = false;
				break;
				
			case MouseEvent.BUTTON2:
				middleHeld = false;
				break;
				
			case MouseEvent.BUTTON3:
				rightHeld = false;
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	public Vec2 getMousePos()
	{
		return mousePos.copy();
	}
	
	public boolean getMouseHeld(MouseButton button)
	{
		return switch (button)
		{
			case LEFT -> leftHeld;
			case MIDDLE -> middleHeld;
			case RIGHT -> rightHeld;
			default -> false;
		};
	}
}
