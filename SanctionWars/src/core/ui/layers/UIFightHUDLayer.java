package core.ui.layers;

import core.entities.players.PlayerNum;
import core.math.Rect;
import core.math.Vec2;
import core.ui.UIImage;
import core.ui.UILayer;
import core.ui.managers.UIManager;

import java.awt.*;

public class UIFightHUDLayer extends UILayer
{
	private static final Rect PLAYER_ONE_HEALTH_BAR_BOUNDS = new Rect(new Vec2(60, 40), new Vec2(500, 80));
	private static final Color HEALTH_BAR_COLOR = new Color(10, 161, 22);
	private static final Color HEALTH_BAR_OUTLINE_COLOR = new Color(30, 28, 28);
	
	private UIImage playerOneHealthBar;
	
	public UIFightHUDLayer()
	{
		super(null);
		Instance = this;
		
		init();
	}
	
	private void init()
	{
		playerOneHealthBar = new UIImage(PLAYER_ONE_HEALTH_BAR_BOUNDS, HEALTH_BAR_COLOR, HEALTH_BAR_OUTLINE_COLOR, UIManager.OUTLINE_THICKNESS);
		addChildElement(playerOneHealthBar);
	}
	
	public static UIImage GetHealthBar(PlayerNum playerNum)
	{
		return switch (playerNum)
		{
			case PLAYER_ONE -> ((UIFightHUDLayer) Instance).playerOneHealthBar;
			case PLAYER_TWO -> null;
			default -> null;
		};
	}
}
