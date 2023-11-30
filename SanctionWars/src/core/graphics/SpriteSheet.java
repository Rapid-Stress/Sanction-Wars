package core.graphics;

import java.awt.image.BufferedImage;

import core.utils.AssetLoader;

public class SpriteSheet 
{
	private Sprite[] sprites;
	private int count;
	
	public SpriteSheet(BufferedImage sheet, int spriteSize) 
	{
		count = (int)(sheet.getWidth() / (float)spriteSize);
		sprites = new Sprite[count];
		
		for (int i = 0; i < count; i++)
		{
			sprites[i] = new Sprite(AssetLoader.CropTexture(sheet, i * spriteSize, 0, spriteSize, spriteSize));
		}
	}
	
	public Sprite getSprite(int index)
	{
		return sprites[index];
	}
	
	public int getCount()
	{
		return count;
	}
}
