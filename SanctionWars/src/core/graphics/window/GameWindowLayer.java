package core.graphics.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import core.Game;
import core.entities.Entity;
import core.entities.manager.EntityManager;
import core.staticobjects.Background;
import core.ui.managers.UIManager;

public class GameWindowLayer extends WindowLayer
{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1491449100326148105L;

	@Override
	public void onRender(Graphics screenGraphics)
	{
		BufferedImage renderTexture = new BufferedImage(Window.RENDER_WIDTH, Window.RENDER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D renderGraphics = renderTexture.createGraphics();
		
		Background bg = Game.Instance.getBackground();
		
		if (bg != null)
			bg.render(renderGraphics);
		
		EntityManager entityManager = Game.Instance.getEntityManager();
		
		if (entityManager != null)
		{
			List<Entity> entities = entityManager.getEntities();
			
			for (Entity entity : entities)
				entity.render(renderGraphics);
		}
		
		UIManager uiManager = Game.Instance.getUIManager();
		
		if (uiManager != null)
			uiManager.render(renderGraphics);
		
		screenGraphics.drawImage(renderTexture, 0, 0, (int)screenSize.x, (int)screenSize.y, null);
	}
}