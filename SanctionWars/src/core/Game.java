package core;

import core.entities.manager.EntityManager;
import core.graphics.rendering.window.GameWindowLayer;
import core.graphics.rendering.window.Window;
import core.graphics.vfx.particlesystems.ParticleSystem;
import core.input.KeyboardManager;
import core.math.Vec2;
import core.staticobjects.Backgrounds;
import core.ui.layers.UIFightHUDLayer;

import java.awt.*;

public class Game
{
	public static Game Instance;
	private static boolean Running;
	public static final int TPS = 60;
	
	public Game(String title, int width, int height)
	{
		Instance = this;
		
		new Window(new GameWindowLayer(), title, width, height);
		new EntityManager();
		new UIFightHUDLayer();
		new ParticleSystem(new ParticleSystem.ParticleSystemArgs()
								   .setIntArg(ParticleSystem.ParticleSystemArgs.IntArgType.COUNT, 80)
								   .setVectorArg(ParticleSystem.ParticleSystemArgs.VectorArgType.SOURCE_POSITION, new Vec2(600, 600))
								   .setFloatArgRange(ParticleSystem.ParticleSystemArgs.FloatArgType.LIFETIME, 240, 320)
								   .setVectorArgRange(ParticleSystem.ParticleSystemArgs.VectorArgType.VELOCITY, new Vec2(-3, -2), new Vec2(3, -3))
								   .setFloatArg(ParticleSystem.ParticleSystemArgs.FloatArgType.GRAVITY_FACTOR, -0.05f)
								   .setVectorArg(ParticleSystem.ParticleSystemArgs.VectorArgType.SIZE, new Vec2(10, 10))
								   .setColorArg(ParticleSystem.ParticleSystemArgs.ColorArgType.COLOR, Color.RED));
		
		Backgrounds.SetCurrentBackground(Backgrounds.MAJESTIC_MOUNTAINS);
		
		Running = true;
	}
	
	public void run()
	{
		double timePerTick = Math.pow(10, 9) / TPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now = 0;
		
		while (Running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1)
			{
				KeyboardManager.Update();
				EntityManager.Update();
				ParticleSystem.Update();
				
				delta--;
			}
		}
	}
}
