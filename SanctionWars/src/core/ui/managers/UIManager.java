package core.ui.managers;

import java.awt.Color;

import core.math.Rect;
import core.math.Vec2;
import core.ui.UIElement;

public class UIManager extends UIElement
{
	public static final UIManager Instance = new UIManager();
	
	public static final Color EDITOR_PRIMARY_COLOR = new Color(42, 42, 42);
	public static final Color EDITOR_SECONDARY_COLOR = new Color(84, 84, 84);
	public static final Color EDITOR_POP_COLOR = new Color(200, 82, 82);
	
	public static final float OUTLINE_THICKNESS = 5;
	public static final Vec2 TITLE_BAR_SIZE = new Vec2(200, 50);
	public static final int SCALE_HANDLE_SIZE = 20;
	public static final int SCALE_HANDLE_SPACING = 5;
	
	public UIManager()
	{
		super(null);
	}
	
	@Override
	protected void onParentChanged()
	{
	
	}
}
