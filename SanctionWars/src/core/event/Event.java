package core.event;

import core.event.EventArgs.EventArgs;

import java.util.ArrayList;

public class Event<T extends EventArgs>
{
	private ArrayList<EventListener<T>> listeners;
	
	public static <Type extends EventArgs> Event<Type> CreateEventDispatcher()
	{
		return new Event<>();
	}
	
	public void addListener(EventListener<T> function)
	{
		if (listeners == null)
			listeners = new ArrayList<>();
		
		listeners.add(function);
	}
	
	public void removeListener(EventListener<T> function)
	{
		listeners.remove(function);
	}
	
	public void dispatch(Object sender, T eventArgs)
	{
		if (listeners == null)
			return;
		
		for (int i = listeners.size() - 1; i >= 0; i--)
		{
			EventListener<T> listener = listeners.get(i);
			listener.apply(sender, listener, eventArgs);
		}
	}

	public final ArrayList<EventListener<T>> getListeners()
	{
		return listeners;
	}
}

