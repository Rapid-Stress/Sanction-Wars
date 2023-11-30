package core.ui;

import core.math.Rect;

public class UILayer extends UIElement
{
	protected static UILayer Instance;
	
	public UILayer(Rect bounds)
	{
		super(bounds);
	}
	
	@Override
	protected void onParentChanged()
	{
	
	}
}
