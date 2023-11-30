package core.ui;

import core.math.Rect;

import java.awt.*;

public class UIImage extends UIElement
{
	private final Color baseColor;
	private final Color outlineColor;
	private final float outlineThickness;
	
	public UIImage(Rect bounds, Color baseColor, Color outlineColor, float outlineThickness)
	{
		super(bounds);
		
		this.baseColor = baseColor;
		this.outlineColor = outlineColor;
		this.outlineThickness = outlineThickness;
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		g.setColor(outlineColor);
		g.setStroke(new BasicStroke(outlineThickness));
		g.drawRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		super.render(g);
	}
	
	@Override
	protected void onParentChanged()
	{
	
	}
}
