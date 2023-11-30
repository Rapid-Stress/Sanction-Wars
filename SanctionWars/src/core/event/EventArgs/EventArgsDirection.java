package core.event.EventArgs;

public class EventArgsDirection extends EventArgs
{
	public float direction;
	
	private EventArgsDirection(float direction)
	{
		this.direction = direction;
	}
	
	public static EventArgsDirection New(float direction)
	{
		return new EventArgsDirection(direction);
	}
}
