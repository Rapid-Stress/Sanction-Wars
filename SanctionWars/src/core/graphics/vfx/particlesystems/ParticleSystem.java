package core.graphics.vfx.particlesystems;

import core.math.Rect;
import core.math.Vec2;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleSystem
{
	public static class Particle
	{
		private final float lifetime;
		private final Rect bounds;
		private final Vec2 velocity;
		private final float gravityFactor;
		private final Color color;
		
		public Particle(float lifetime, Rect bounds, Vec2 velocity, float gravityFactor, Color color)
		{
			this.lifetime = lifetime;
			this.bounds = bounds;
			this.velocity = velocity;
			this.gravityFactor = gravityFactor;
			this.color = color;
		}
		
		public void update()
		{
			bounds.x += velocity.x;
			bounds.y += velocity.y;
			
			velocity.y -= gravityFactor;
		}
		
		public void render(Graphics g)
		{
			g.setColor(color);
			g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		}
		
		public float getLifetime()
		{
			return lifetime;
		}
	}
	
	public static class ParticleSystemArgs
	{
		public enum IntArgType
		{
			COUNT
		}
		
		public enum FloatArgType
		{
			LIFETIME,
			GRAVITY_FACTOR
		}
		
		public enum VectorArgType
		{
			SOURCE_POSITION,
			VELOCITY,
			SIZE
		}
		
		public enum ColorArgType
		{
			COLOR
		}
		
		private int particleCountMin = 1;
		private float particleLifetimeMin = 1;
		private float gravityFactorMin = 0;
		private Vec2 sourcePositionMin = new Vec2(0, 0);
		private Vec2 particleVelocityMin = new Vec2(0, 0);
		private Vec2 particleSizeMin = new Vec2(0, 0);
		private Color particleColorMin = Color.WHITE;
		
		private int particleCountMax = 1;
		private float particleLifetimeMax = 1;
		private float gravityFactorMax = 0;
		private Vec2 sourcePositionMax = new Vec2(0, 0);
		private Vec2 particleVelocityMax = new Vec2(0, 0);
		private Vec2 particleSizeMax = new Vec2(0, 0);
		private Color particleColorMax = Color.WHITE;
		
		public ParticleSystemArgs setIntArg(IntArgType type, int value)
		{
			if (Objects.requireNonNull(type) == IntArgType.COUNT)
			{
				particleCountMax = value;
				particleCountMin = value;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setFloatArg(FloatArgType type, float value)
		{
			switch (Objects.requireNonNull(type))
			{
				case LIFETIME:
					particleLifetimeMax = value;
					particleLifetimeMin = value;
					break;
					
				case GRAVITY_FACTOR:
					gravityFactorMax = value;
					gravityFactorMin = value;
					break;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setVectorArg(VectorArgType type, Vec2 value)
		{
			switch (Objects.requireNonNull(type))
			{
				case SOURCE_POSITION:
					sourcePositionMax = value.copy();
					sourcePositionMin = value.copy();
					break;
					
				case VELOCITY:
					particleVelocityMax = value.copy();
					particleVelocityMin = value.copy();
					break;
					
				case SIZE:
					particleSizeMax = value.copy();
					particleSizeMin = value.copy();
					break;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setColorArg(ColorArgType type, Color value)
		{
			if (Objects.requireNonNull(type) == ColorArgType.COLOR)
			{
				particleColorMax = new Color(value.getRed(), value.getGreen(), value.getBlue(), value.getAlpha());
				particleColorMin = new Color(value.getRed(), value.getGreen(), value.getBlue(), value.getAlpha());
			}
			
			return this;
		}
		
		public ParticleSystemArgs setIntArgRange(IntArgType type, int minValue, int maxValue)
		{
			if (Objects.requireNonNull(type) == IntArgType.COUNT)
			{
				particleCountMax = maxValue;
				particleCountMin = minValue;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setFloatArgRange(FloatArgType type, float minValue, float maxValue)
		{
			switch (Objects.requireNonNull(type))
			{
				case LIFETIME:
					particleLifetimeMax = maxValue;
					particleLifetimeMin = minValue;
					break;
				
				case GRAVITY_FACTOR:
					gravityFactorMax = maxValue;
					gravityFactorMin = minValue;
					break;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setVectorArgRange(VectorArgType type, Vec2 minValue, Vec2 maxValue)
		{
			switch (Objects.requireNonNull(type))
			{
				case SOURCE_POSITION:
					sourcePositionMax = maxValue.copy();
					sourcePositionMin = minValue.copy();
					break;
				
				case VELOCITY:
					particleVelocityMax = maxValue.copy();
					particleVelocityMin = minValue.copy();
					break;
				
				case SIZE:
					particleSizeMax = maxValue.copy();
					particleSizeMin = minValue.copy();
					break;
			}
			
			return this;
		}
		
		public ParticleSystemArgs setColorArgRange(ColorArgType type, Color minValue, Color maxValue)
		{
			if (Objects.requireNonNull(type) == ColorArgType.COLOR)
			{
				particleColorMax = new Color(maxValue.getRed(), maxValue.getGreen(), maxValue.getBlue(), maxValue.getAlpha());
				particleColorMin = new Color(minValue.getRed(), minValue.getGreen(), minValue.getBlue(), minValue.getAlpha());
			}
			
			return this;
		}
		
		public int getIntArg(IntArgType type)
		{
			if (Objects.requireNonNull(type) == IntArgType.COUNT)
				return ThreadLocalRandom.current().nextInt(particleCountMin, particleCountMax + 1);
			
			return 0;
		}
		
		public float getFloatArg(FloatArgType type)
		{
			return switch (Objects.requireNonNull(type))
			{
				case LIFETIME -> ThreadLocalRandom.current().nextFloat(particleLifetimeMin, particleLifetimeMax + 0.01f);
				case GRAVITY_FACTOR -> ThreadLocalRandom.current().nextFloat(gravityFactorMin, gravityFactorMax + 0.01f);
			};
		}
		
		public Vec2 getVectorArg(VectorArgType type)
		{
			return switch (Objects.requireNonNull(type))
			{
				case SOURCE_POSITION -> Vec2.RandomVector(sourcePositionMin, sourcePositionMax);
				case VELOCITY -> Vec2.RandomVector(particleVelocityMin, particleVelocityMax);
				case SIZE -> Vec2.RandomVector(particleSizeMin, particleSizeMax);
			};
		}
		
		public Color getColorArg(ColorArgType type)
		{
			if (Objects.requireNonNull(type) == ColorArgType.COLOR)
			{
				int newR = ThreadLocalRandom.current().nextInt(
						Math.min(particleColorMin.getRed(), particleColorMax.getRed()),
						Math.max(particleColorMin.getRed(), particleColorMax.getRed()) + 1);
				int newG = ThreadLocalRandom.current().nextInt(
						Math.min(particleColorMin.getGreen(), particleColorMax.getGreen()),
						Math.max(particleColorMin.getGreen(), particleColorMax.getGreen()) + 1);
				int newB = ThreadLocalRandom.current().nextInt(
						Math.min(particleColorMin.getBlue(), particleColorMax.getBlue()),
						Math.max(particleColorMin.getBlue(), particleColorMax.getBlue()) + 1);
				int newA = ThreadLocalRandom.current().nextInt(
						Math.min(particleColorMin.getAlpha(), particleColorMax.getAlpha()),
						Math.max(particleColorMin.getAlpha(), particleColorMax.getAlpha()) + 1);
				
				return new Color(newR, newG, newB, newA);
			}
			
			return null;
		}
	}
	
	private static final List<ParticleSystem> RunningSystems = Collections.synchronizedList(new ArrayList<>());
	
	private final ParticleSystemArgs args;
	private final List<Particle> particles;
	
	private float elapsedTime;
	
	public ParticleSystem(ParticleSystemArgs args)
	{
		this.args = args;
		
		int particleCount = args.getIntArg(ParticleSystemArgs.IntArgType.COUNT);

		particles = Collections.synchronizedList(new ArrayList<>(particleCount));
		for (int i = 0; i < particleCount; i++)
		{
			float particleLifetime = args.getFloatArg(ParticleSystemArgs.FloatArgType.LIFETIME);
			Vec2 sourcePosition = args.getVectorArg(ParticleSystemArgs.VectorArgType.SOURCE_POSITION);
			Vec2 particleSize = args.getVectorArg(ParticleSystemArgs.VectorArgType.SIZE);
			Vec2 particleVelocity = args.getVectorArg(ParticleSystemArgs.VectorArgType.VELOCITY);
			float gravityFactor = args.getFloatArg(ParticleSystemArgs.FloatArgType.GRAVITY_FACTOR);
			Color particleColor = args.getColorArg(ParticleSystemArgs.ColorArgType.COLOR);
			
			particles.add(new Particle(particleLifetime, new Rect(sourcePosition.x, sourcePosition.y, particleSize.x, particleSize.y),
									   particleVelocity, gravityFactor, particleColor));
		}
		
		RunningSystems.add(this);
	}
	
	public static void Update()
	{
		for (ParticleSystem particleSystem : RunningSystems)
			particleSystem.update();
	}
	
	public static void Render(Graphics g)
	{
		for (ParticleSystem particleSystem : RunningSystems)
			particleSystem.render(g);
	}
	
	public void update()
	{
		ArrayList<Particle> diedParticles = null;
		
		for (Particle particle : particles)
		{
			particle.update();
			
			if (elapsedTime >= particle.getLifetime())
			{
				if (diedParticles == null)
					diedParticles = new ArrayList<>();
				
				diedParticles.add(particle);
			}
		}
		
		if (diedParticles != null)
			particles.removeAll(diedParticles);
		
		elapsedTime++;
	}
	
	public void render(Graphics g)
	{
		for (Particle particle : particles)
			particle.render(g);
	}
	
	public ParticleSystemArgs getArgs()
	{
		return args;
	}
}
