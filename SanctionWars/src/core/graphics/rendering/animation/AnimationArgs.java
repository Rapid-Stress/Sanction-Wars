package core.graphics.rendering.animation;

import java.awt.image.BufferedImage;

public class AnimationArgs
{
	public BufferedImage sheet;
	public FrameArgs frameArgs;
	public int spriteSize;
	public boolean looping;
	
	public AnimationArgs(BufferedImage sheet, FrameArgs frameArgs, int spriteSize, boolean looping)
	{
		this.sheet = sheet;
		this.frameArgs = frameArgs;
		this.spriteSize = spriteSize;
		this.looping = looping;
		
	}
}
