package core.graphics.rendering.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serial;

import core.ui.managers.UIManager;
import editor.Editor;

public class EditorWindowLayer extends WindowLayer
{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2330506606353052600L;

	@Override
	public void onRender(Graphics screenGraphics)
	{
		BufferedImage renderTexture = new BufferedImage(Window.RENDER_WIDTH, Window.RENDER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D renderGraphics = renderTexture.createGraphics();
		
		UIManager uiManager = Editor.Instance.getUIManager();
		
		if (uiManager != null)
			uiManager.render(renderGraphics);
		
		screenGraphics.drawImage(renderTexture, 0, 0, (int)screenSize.x, (int)screenSize.y, null);
	}
}
