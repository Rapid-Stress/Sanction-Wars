package core.ui;

import core.math.Rect;
import core.math.Vec2;

import java.awt.*;

public class UIText extends UIElement
{
	private final String text;
	private final Color textColor;
	private final int fontSize;
	
	private Vec2 offsetFromParent;
	
	public UIText(Rect bounds, String text, Color textColor, int fontSize)
	{
		super(bounds);
		
		this.text = text;
		this.textColor = textColor;
		this.fontSize = fontSize;
	}
	
	@Override
	public void update()
	{
		if (getParent() != null)
		{
			bounds.x += getParent().bounds.x + offsetFromParent.x;
			bounds.y += getParent().bounds.y + offsetFromParent.y;
		}
		
		super.update();
	}
	
	@Override
	public void render(Graphics2D g)
	{
		float textWidth = (fontSize / 2.0f) * text.length();
		
		float xPos;
		float yPos;
		
		yPos = (bounds.y + (fontSize / 2.0f) / 2.0f) + (bounds.height / 2.0f);
		xPos = (bounds.x - (textWidth / 1.5f) + (bounds.width / 2.0f));
		
		g.setColor(textColor);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
		g.drawString(text, xPos, yPos);
		
		super.render(g);
	}
	
	@Override
	protected void onParentChanged()
	{
		offsetFromParent = new Vec2(bounds.x + getParent().bounds.x, bounds.y + getParent().bounds.y);
	}
}
