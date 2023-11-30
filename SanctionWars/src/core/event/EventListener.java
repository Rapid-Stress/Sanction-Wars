package core.event;

@FunctionalInterface
public interface EventListener<Args>
{
	Void apply(Object sender, EventListener<Args> listenerInstance, Args event);
}
