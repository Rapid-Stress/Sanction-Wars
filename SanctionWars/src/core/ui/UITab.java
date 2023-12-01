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
			bounds.x += mouseDelta.x;
			bounds.y += mouseDelta.y;
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
			bounds.width += mouseDelta.x;
			bounds.height += mouseDelta.y;;
		}
	}
	
	public void updateTabPos()
	{
		titleBarBounds.update(bounds.x, bounds.y, titleBarBounds.width, titleBarBounds.height);
		titleBarBounds.width = bounds.width;
		
		Vec2 scaleHandlePos = new Vec2((bounds.x + bounds.width) - scaleHandleBounds.width - scaleHandleSpacing,
									   (bounds.y + bounds.height) - scaleHandleBounds.height - scaleHandleSpacing);
		scaleHandleBounds.update(scaleHandlePos.x, scaleHandlePos.y, scaleHandleBounds.width, scaleHandleBounds.height);
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		
		g.setColor(titleBarColor);
		g.fillRect((int) titleBarBounds.x, (int) titleBarBounds.y, (int) titleBarBounds.width, (int) titleBarBounds.height);
		
		g.setColor(accentColor);
		g.fillRect((int) scaleHandleBounds.x, (int) scaleHandleBounds.y, (int) scaleHandleBounds.width, (int) scaleHandleBounds.height);
		
		g.setStroke(new BasicStroke(outlineThickness));
		g.drawRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		
		super.render(g);
	}
}
