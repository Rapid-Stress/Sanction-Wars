package core.input;

import core.event.Event;
import core.event.EventArgs.EventArgs;
import core.event.EventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardManager implements KeyListener
{
	private enum KeyState
	{
		IDLE,
		HELD,
		PRESSED,
		RELEASED
	}
	
	public enum KeyListenType
	{
		ON_HELD,
		ON_PRESSED,
		ON_RELEASED
	}
	
	public static final KeyboardManager Instance = new KeyboardManager();
	private static final int MAX_KEY = 65489;
	
	private final ArrayList<Event<EventArgs>> onKeyHeldEventPool;
	private final ArrayList<Event<EventArgs>> onKeyPressedEventPool;
	private final ArrayList<Event<EventArgs>> onKeyReleasedEventPool;
	
	private final boolean[] keyHeldBuffer;
	private final KeyState[] keyStateBuffer;
	
	private KeyboardManager()
	{
		onKeyHeldEventPool = new ArrayList<>(MAX_KEY);
		onKeyPressedEventPool = new ArrayList<>(MAX_KEY);
		onKeyReleasedEventPool = new ArrayList<>(MAX_KEY);
		
		keyHeldBuffer = new boolean[MAX_KEY];
		keyStateBuffer = new KeyState[MAX_KEY];
		
		for (int i = 0; i < MAX_KEY; i++)
		{
			onKeyHeldEventPool.add(Event.CreateEventDispatcher());
			onKeyPressedEventPool.add(Event.CreateEventDispatcher());
			onKeyReleasedEventPool.add(Event.CreateEventDispatcher());
		}
	}
	
	public static void ListenToKey(int keycode, KeyListenType listenType, EventListener<EventArgs> listener)
	{
		switch (listenType)
		{
			case ON_HELD -> Instance.onKeyHeldEventPool.get(keycode).addListener(listener);
			case ON_PRESSED -> Instance.onKeyPressedEventPool.get(keycode).addListener(listener);
			case ON_RELEASED -> Instance.onKeyReleasedEventPool.get(keycode).addListener(listener);
		}
	}
	
	public static void Update()
	{
		for (int i = 0; i < MAX_KEY; i++)
		{
			if (Instance.keyHeldBuffer[i])
				Instance.onKeyHeldEventPool.get(i).dispatch(Instance, EventArgs.Empty());
			
			if (Instance.keyStateBuffer[i] == KeyState.PRESSED)
			{
				Instance.keyStateBuffer[i] = KeyState.HELD;
				Instance.onKeyPressedEventPool.get(i).dispatch(Instance, EventArgs.Empty());
			}
			else if (Instance.keyStateBuffer[i] == KeyState.RELEASED)
			{
				Instance.keyStateBuffer[i] = KeyState.IDLE;
				Instance.onKeyReleasedEventPool.get(i).dispatch(Instance, EventArgs.Empty());
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		keyHeldBuffer[e.getKeyCode()] = true;
		
		if (keyStateBuffer[e.getKeyCode()] != KeyState.HELD)
			keyStateBuffer[e.getKeyCode()] = KeyState.PRESSED;
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		keyHeldBuffer[e.getKeyCode()] = false;
		keyStateBuffer[e.getKeyCode()] = KeyState.RELEASED;
	}
}
