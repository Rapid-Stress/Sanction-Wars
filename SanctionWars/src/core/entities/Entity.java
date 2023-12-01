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
		this.bounds = new Rect(bounds.x - (int) (bounds.width / 2.0f),
							   bounds.y - (int) (bounds.height / 2.0f),
							   bounds.width, bounds.height);
		
		this.colliderBounds = new Rect(this.bounds.x + colliderBounds.x, this.bounds.y + colliderBounds.y,
									   colliderBounds.width, colliderBounds.height);
		this.collider = new BoxCollider(this.colliderBounds);
		
		this.speed = speed;
		this.flipped = flipped;
	}
	
	protected void move(float moveDir)
	{
		if (moveDir == 0)
			return;
		
		float move = moveDir * speed;
		
		float prevX = colliderBounds.x;
		colliderBounds.x += move;
		
		boolean colliding = detectCollision();
		
		colliderBounds.x = prevX;
		
		if (!colliding)
		{
			bounds.x += move;
			colliderBounds.x += move;
			
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
}
