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
			bounds.pos = Vec2.Add(getParent().bounds.pos, offsetFromParent);
		
		super.update();
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		g.setColor(accentColor);
		g.setStroke(new BasicStroke(outlineThickness));
		g.drawRect((int) bounds.pos.x, (int) bounds.pos.y, (int) bounds.size.x, (int) bounds.size.y);
		
		super.render(g);
	}
	
	@Override
	protected void onParentChanged()
	{
		offsetFromParent = Vec2.Sub(bounds.pos, getParent().bounds.pos);
	}
}
