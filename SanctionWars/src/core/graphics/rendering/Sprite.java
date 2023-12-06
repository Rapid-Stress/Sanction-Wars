package core.graphics.rendering;

import java.awt.image.BufferedImage;

public class Sprite 
{
	private BufferedImage texture;
	private BufferedImage xFlippedTexture;
	private BufferedImage yFlippedTexture;
	private BufferedImage xyFlippedTexture;
	
	public Sprite(BufferedImage texture)
	{
		this.texture = texture;
		
		generateVariants();
	}
	
	private void generateVariants()
	{
		xFlippedTexture = new BufferedImage(texture.getWidth(), texture.getHeight(), texture.getType());
		
		for (int y = 0; y < texture.getWidth(); y++)
		{
			for (int x = 0; x < texture.getHeight(); x++)
			{
				int readX = (texture.getWidth() - 1) - x;
				xFlippedTexture.setRGB(x, y, texture.getRGB(readX, y));
			}
		}
		
		yFlippedTexture = new BufferedImage(texture.getWidth(), texture.getHeight(), texture.getType());
		
		for (int y = 0; y < texture.getWidth(); y++)
		{
			for (int x = 0; x < texture.getHeight(); x++)
			{
				int readY = (texture.getHeight() - 1) - y;
				yFlippedTexture.setRGB(x, y, texture.getRGB(x, readY));
			}
		}
		
		xyFlippedTexture = new BufferedImage(texture.getWidth(), texture.getHeight(), texture.getType());
		
		for (int y = 0; y < texture.getWidth(); y++)
		{
			for (int x = 0; x < texture.getHeight(); x++)
			{
				int readX = (texture.getWidth() - 1) - x;
				int readY = (texture.getHeight() - 1) - y;
				xyFlippedTexture.setRGB(x, y, texture.getRGB(readX, readY));
			}
		}
	}
	
	public BufferedImage getTexture()
	{
		return texture;
	}
	
	public BufferedImage getXFlippedTexture()
	{
		return xFlippedTexture;
	}
	
	public BufferedImage getYFlippedTexture()
	{
		return yFlippedTexture;
	}
	
	public BufferedImage getXYFlippedTexture()
	{
		return xyFlippedTexture;
	}
	
	public void setTexture(BufferedImage newTexture)
	{
		texture = newTexture;
		generateVariants();
	}
}
