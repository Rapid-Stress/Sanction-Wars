package core.ui;

import core.math.Rect;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class UIElement
{
	private ArrayList<UIElement> childElements;
	private UIElement parent;
	
	protected final Rect bounds;
	
	public UIElement(Rect bounds)
	{
		this.bounds = bounds;
	}
	
	protected abstract void onParentChanged();
	
	public void update()
	{
		if (childElements == null)
			return;
		
		for (UIElement child : childElements)
			child.update();
	}
	
	public void render(Graphics2D g)
	{
		if (childElements == null)
			return;
		
		for (UIElement child : childElements)
			child.render(g);
	}
	
	public void addChildElement(UIElement uiElement)
	{
		if (childElements == null)
			childElements = new ArrayList<>();
		
		childElements.add(uiElement);
	}
	
	public void setParent(UIElement uiElement)
	{
		parent = uiElement;
		
		onParentChanged();
	}
	
	public ArrayList<UIElement> getChildElements()
	{
		return childElements;
	}
	
	public UIElement getParent() { return parent; }
	
	public Rect getBounds() { return bounds; }
}
