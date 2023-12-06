package core.graphics.rendering.animation;

public class FrameArgs
{
	public int[] tickPerFrame;
	public int frameCount;
	
	public FrameArgs(int[] tickPerFrame)
	{
		this.tickPerFrame = tickPerFrame;
		frameCount = tickPerFrame.length;
	}
	
	public FrameArgs(int uniformTickPerFrame, int frameCount)
	{
		tickPerFrame = new int[frameCount];
		this.frameCount = frameCount;
		
		for (int i = 0; i < frameCount; i++)
			tickPerFrame[i] = uniformTickPerFrame;
	}
}
