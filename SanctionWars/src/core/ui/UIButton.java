package core.ui;

import core.math.Rect;
import core.math.Vec2;

import java.awt.*;

public class UIButton extends UIElement
{
	private final Color baseColor;
	private final Color accentColor;
	private final float outlineThickness;
	
	private Vec2 offsetFromParent = Vec2.One();
	
	public UIButton(Rect bounds, Color baseColor, Color accentColor, float outlineThickness)
	{
		super(bounds);
		
		this.baseColor = baseColor;
		this.accentColor = accentColor;
		this.outlineThickness = outlineThickness;
	}
	
	@Override
	public void update()
	{
		if (getParent() != null)
		{
			bounds.x -= (getParent().bounds.x + offsetFromParent.x);
			bounds.y -= (getParent().bounds.y + offsetFromParent.y);
		}
		
		super.update();
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		
		g.setColor(accentColor);
		g.setStroke(new BasicStroke(outlineThickness));
		g.drawRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		
		super.render(g);
	}
	
	@Override
	protected void onParentChanged()
	{
		offsetFromParent = new Vec2(bounds.x - getParent().bounds.x, bounds.y - getParent().bounds.y);
	}
}
