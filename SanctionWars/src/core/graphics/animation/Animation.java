package core.graphics.animation;

import core.event.Event;
import core.event.EventArgs.EventArgs;
import core.graphics.Sprite;
import core.graphics.SpriteSheet;

public class Animation 
{
	public Event<EventArgs> onFinished = Event.CreateEventDispatcher();
	public Event<EventArgs> onStarted = Event.CreateEventDispatcher();
	public Event<EventArgs> onUpdate = Event.CreateEventDispatcher();
	public Event<EventArgs> onRestart = Event.CreateEventDispatcher();
	
	private final SpriteSheet animSheet;
	private final AnimationArgs args;
	private final FrameArgs frameArgs;
	
	private Sprite currentFrame;
	private int frameIndex;
	private int tick;
	
	private boolean started = false;
	
	public Animation(AnimationArgs args)
	{
		animSheet = new SpriteSheet(args.sheet, args.spriteSize);
		this.args = args;
		
		frameArgs = args.frameArgs;
		currentFrame = animSheet.getSprite(0);
		
		if (frameArgs.frameCount != animSheet.getCount())
		{
			System.err.println("Invalid Animation!");
			System.exit(1);
		}
	}
	
	public void update()
	{
		if (!started)
		{
			started = true;
			onStarted.dispatch(this, EventArgs.Empty());
		}
		
		tick++;
		
		onUpdate.dispatch(this, EventArgs.Empty());
		
		if (tick >= frameArgs.tickPerFrame[frameIndex])
		{
			if (frameIndex == 0)
				onRestart.dispatch(this, EventArgs.Empty());
			
			if ((frameIndex + 1) == animSheet.getCount())
				onFinished.dispatch(this, EventArgs.Empty());
			
			if (args.looping)
				frameIndex = (frameIndex + 1) % animSheet.getCount();
			else
				if (frameIndex < animSheet.getCount() - 1)
					frameIndex++;
			
			currentFrame = animSheet.getSprite(frameIndex);
			tick = 0;
		}
	}
	
	public void restart()
	{
		started = false;
		tick = 0;
		frameIndex = 0;
		
		onRestart.dispatch(this, EventArgs.Empty());
	}
	
	public Sprite getFrame()
	{
		return currentFrame;
	}
	
	public AnimationArgs getArgs()
	{
		return args;
	}
}
