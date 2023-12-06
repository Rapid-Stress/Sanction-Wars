package core.graphics.rendering.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serial;

import core.entities.Entity;
import core.entities.manager.EntityManager;
import core.graphics.vfx.particlesystems.ParticleSystem;
import core.staticobjects.Backgrounds;
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
		
		Backgrounds.GetBackground().render(renderGraphics);
		
		for (Entity entity : EntityManager.Instance.getEntities())
			entity.render(renderGraphics);
		
		ParticleSystem.Render(renderGraphics);
		
		UIManager uiManager = UIManager.Instance;
		uiManager.render(renderGraphics);
		
		screenGraphics.drawImage(renderTexture, 0, 0, (int)screenSize.x, (int)screenSize.y, null);
	}
}