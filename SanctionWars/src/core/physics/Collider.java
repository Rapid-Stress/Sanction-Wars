package core.physics;

import core.math.Rect;
import core.math.Vec2;

public abstract class Collider
{
	protected Rect bounds;
	protected Vec2[] points;
	
	public Collider(Rect bounds, Vec2[] points)
	{
		this.bounds = bounds;
		this.points = points;
	}
	
	public abstract boolean isColiding(Vec2 point);
	public abstract boolean isCollding(Collider other);
	public abstract void update(Rect newBounds);
	
	public Vec2[] getPoints() { return points; };
	public Rect getBounds() { return bounds; }
}
