package core.physics;

import core.math.Rect;
import core.math.Vec2;

public class BoxCollider extends Collider
{
	public BoxCollider(Rect bounds)
	{
		super(bounds, new Vec2[]
				{
						bounds.pos.copy(),
						new Vec2(bounds.pos.x + bounds.size.x, bounds.pos.y),
						new Vec2(bounds.pos.x, bounds.pos.y + bounds.size.y),
						Vec2.Add(bounds.pos, bounds.size)
				});
	}
	
	@Override
	public boolean isColiding(Vec2 point)
	{
		return bounds.containsPoint(point);
	}
	
	@Override
	public boolean isCollding(Collider other)
	{
		boolean colliding = false;
		
		for (Vec2 point : other.getPoints())
		{
			if (bounds.containsPoint(point))
			{
				colliding = true;
				break;
			}
		}
		
		return colliding;
	}
	
	@Override
	public void update(Rect newBounds)
	{
		bounds.update(newBounds);
		
		points[0].update(bounds.pos);
		points[1].update(bounds.pos.x + bounds.size.x, bounds.pos.y);
		points[2].update(bounds.pos.x, bounds.pos.y + bounds.size.y);
		points[3].update(bounds.pos.x + bounds.size.x, bounds.pos.y + bounds.size.y);
	}
}
