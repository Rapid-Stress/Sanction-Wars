package core.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import core.input.MouseManager;
import core.input.MouseManager.MouseButton;
import core.math.Rect;
import core.math.Vec2;

public class UITab extends UIElement
{
	private final Rect titleBarBounds;
	private final Rect scaleHandleBounds;
	private final Color baseColor;
	private final Color titleBarColor;
	private final Color accentColor;
	private final float scaleHandleSpacing;
	private final float outlineThickness;
	
	private final Vec2 mousePos = Vec2.Zero();
	private final Vec2 prevMousePos = Vec2.Zero();
	private boolean dragging = false;
	private boolean scaling = false;
	
	public UITab(Rect tabBounds, Rect titleBarBounds, Rect scaleHandleBounds,
				 Color baseColor, Color titleBarColor, Color accentColor,
				 float scaleHandleSpacing, float outlineThickness)
	{
		super(tabBounds);
		
		this.titleBarBounds = titleBarBounds;
		this.scaleHandleBounds = scaleHandleBounds;
		this.baseColor = baseColor;
		this.titleBarColor = titleBarColor;
		this.accentColor = accentColor;
		this.scaleHandleSpacing = scaleHandleSpacing;
		this.outlineThickness = outlineThickness;
	}
	
	@Override
	protected void onParentChanged()
	{
	
	}
	
	@Override
	public void update()
	{
		mousePos.update(MouseManager.Instance.getMousePos().toWorldCoord());
		
		handleDragging();
		handleScale();
		updateTabPos();
		
		prevMousePos.update(mousePos.copy());
		
		super.update();
	}
	
	public void handleDragging()
	{
		if (MouseManager.Instance.getMouseHeld(MouseButton.LEFT)
				&& titleBarBounds.containsPoint(mousePos)
				&& !dragging
				&& !scaling)
			dragging = true;
		
		if (!MouseManager.Instance.getMouseHeld(MouseButton.LEFT) && dragging)
			dragging = false;
		
		if (dragging)
		{
			Vec2 mouseDelta = Vec2.Sub(mousePos, prevMousePos);
			bounds.pos.add(mouseDelta);
		}
	}
	
	public void handleScale()
	{
		if (MouseManager.Instance.getMouseHeld(MouseButton.LEFT)
				&& scaleHandleBounds.containsPoint(mousePos)
				&& !scaling
				&& !dragging)
			scaling = true;
		
		if (!MouseManager.Instance.getMouseHeld(MouseButton.LEFT) && scaling)
			scaling = false;
		
		if (scaling)
		{
			Vec2 mouseDelta = Vec2.Sub(mousePos, prevMousePos);
			bounds.size.add(mouseDelta);
		}
	}
	
	public void updateTabPos()
	{
		titleBarBounds.pos.update(bounds.pos);
		titleBarBounds.size.x = bounds.size.x;
		scaleHandleBounds.pos.update(Vec2.Sub(Vec2.Add(bounds.pos, bounds.size), scaleHandleBounds.size).sub(Vec2.One().mult(scaleHandleSpacing)));
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		g.setColor(titleBarColor);
		g.fillRect((int) titleBarBounds.pos.x, (int) titleBarBounds.pos.y, (int) titleBarBounds.size.x, (int) titleBarBounds.size.y);
		
		g.setColor(accentColor);
		g.fillRect((int) scaleHandleBounds.pos.x, (int) scaleHandleBounds.pos.y, (int) scaleHandleBounds.size.x, (int) scaleHandleBounds.size.y);
		
		g.setStroke(new BasicStroke(outlineThickness));
		g.drawRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		super.render(g);
	}
}
