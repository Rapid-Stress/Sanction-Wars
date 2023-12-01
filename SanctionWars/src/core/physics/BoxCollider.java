package core.physics;

import core.math.Rect;
import core.math.Vec2;

public class BoxCollider extends Collider
{
	public BoxCollider(Rect bounds)
	{
		super(bounds, new Vec2[]
				{
						new Vec2(bounds.x, bounds.y),
						new Vec2(bounds.x + bounds.width, bounds.y),
						new Vec2(bounds.x, bounds.y + bounds.height),
						new Vec2(bounds.x + bounds.width, bounds.y + bounds.height),
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
		
		points[0].update(bounds.x, bounds.y);
		points[1].update(bounds.x + bounds.width, bounds.y);
		points[2].update(bounds.x, bounds.y + bounds.height);
		points[3].update(bounds.x + bounds.width, bounds.y + bounds.height);
	}
}
