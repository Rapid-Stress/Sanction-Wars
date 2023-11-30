package core.entities;

import java.awt.Graphics2D;
import java.util.List;

import core.Game;
import core.math.Rect;
import core.math.Vec2;
import core.physics.BoxCollider;
import core.physics.Collider;

public abstract class Entity
{
	protected Rect bounds;
	protected Rect colliderBounds;
	protected Collider collider;
	protected float speed;
	protected boolean flipped;
	
	public Entity(Rect bounds, Rect colliderBounds, float speed, boolean flipped)
	{
		this.bounds = new Rect(new Vec2
									   (bounds.pos.x - (int) (bounds.size.x / 2.0f),
										bounds.pos.y - (int) (bounds.size.y / 2.0f)),
							   new Vec2(bounds.size.x, bounds.size.y));
		
		this.colliderBounds = new Rect(Vec2.Add(this.bounds.pos, colliderBounds.pos), colliderBounds.size);
		this.collider = new BoxCollider(this.colliderBounds);
		
		this.speed = speed;
		this.flipped = flipped;
	}
	
	protected void move(float moveDir)
	{
		if (moveDir == 0)
			return;
		
		Vec2 move = new Vec2(moveDir * speed, 0);
		
		Vec2 prevPos = colliderBounds.pos;
		colliderBounds.pos = Vec2.Add(colliderBounds.pos, move);
		
		boolean colliding = detectCollision();
		
		colliderBounds.pos = prevPos;
		
		if (!colliding)
		{
			bounds.pos.add(move);
			colliderBounds.pos.add(move);
			
			collider.update(colliderBounds);
		}
	}
	
	protected boolean detectCollision()
	{
		boolean colliding = false;
		
		List<Entity> entities = Game.Instance.getEntityManager().getEntities();
		
		for (Entity entity : entities)
		{
			if (entity == this)
				continue;
			
			if (collider.isCollding(entity.collider))
			{
				colliding = true;
				break;
			}
		}
		
		return colliding;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public void setPosition(Vec2 newPos)
	{
		bounds.pos = newPos;
	}
	
	public void setSize(Vec2 newSize)
	{
		bounds.size = newSize;
	}
	
	public void setSpeed(float newSpeed)
	{
		speed = newSpeed;
	}
	
	public Vec2 getPosition()
	{
		return bounds.pos;
	}
	
	public Vec2 getSize()
	{
		return bounds.size;
	}
	
	public float getSpeed()
	{
		return speed;
	}
}
