package core.ui.layers;

import core.graphics.window.Window;
import core.math.Rect;
import core.math.Vec2;
import core.ui.UIButton;
import core.ui.UIElement;
import core.ui.UITab;
import core.ui.UIText;
import core.ui.managers.UIManager;

public class UIObjectEditorLayer extends UIElement
{
	private static final Vec2 TAB_POSITION = Vec2.One().mult(10);
	private static final Vec2 TAB_SIZE = new Vec2(300, Window.RENDER_HEIGHT - 100);
	private static final Rect TAB_BOUNDS = new Rect(TAB_POSITION, TAB_SIZE);
	
	public UIObjectEditorLayer()
	{
		super(null);
		init();
	}
	
	private void init()
	{
		Rect titleBarBounds = new Rect(TAB_POSITION, UIManager.TITLE_BAR_SIZE);
		
		Rect scaleHandleBounds = new Rect(Vec2.Sub(Vec2.Add(TAB_POSITION, TAB_SIZE), Vec2.One().mult(UIManager.SCALE_HANDLE_SIZE).sub(Vec2.One().mult(UIManager.SCALE_HANDLE_SPACING))),
										  Vec2.One().mult(UIManager.SCALE_HANDLE_SIZE));
		
		UITab newTab = new UITab(TAB_BOUNDS, titleBarBounds, scaleHandleBounds,
								 UIManager.EDITOR_PRIMARY_COLOR, UIManager.EDITOR_SECONDARY_COLOR, UIManager.EDITOR_POP_COLOR,
								 UIManager.SCALE_HANDLE_SPACING, UIManager.OUTLINE_THICKNESS);
		
		int fontSize = 18;
		
		Rect titleTextRect = new Rect(Vec2.One(), Vec2.One()).update(titleBarBounds);
		UIText newTitleText = new UIText(titleTextRect, "OBJECT SETTINGS", UIManager.EDITOR_POP_COLOR, fontSize);
		
		Vec2 buttonPosition = new Vec2(50, 100);
		Vec2 buttonSize = new Vec2(100, 40);
		Rect buttonBounds = new Rect(buttonPosition, buttonSize);
		
		UIButton newButton = new UIButton(buttonBounds, UIManager.EDITOR_SECONDARY_COLOR, UIManager.EDITOR_POP_COLOR, UIManager.OUTLINE_THICKNESS);
		UIText newButtonText = new UIText(buttonBounds, "BUTTON", UIManager.EDITOR_POP_COLOR, fontSize);
		
		newTab.addChildElement(newButton);
		newTab.addChildElement(newTitleText);
		newButton.addChildElement(newButtonText);
		
		newButton.setParent(newTab);
		newTitleText.setParent(newTab);
		newButtonText.setParent(newButton);
		
		addChildElement(newTab);
	}
	
	@Override
	protected void onParentChanged()
	{
	}
}
