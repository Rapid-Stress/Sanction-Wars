package core.math;

public class Rect
{
	public Vec2 pos;
	public Vec2 size;
	
	public Rect(Vec2 pos, Vec2 size)
	{
		this.pos = pos;
		this.size = size;
	}
	
	public static boolean ContainsPoint(Rect rect, Vec2 point)
	{
		return point.x >= rect.pos.x && point.x <= rect.pos.x + rect.size.x && point.y >= rect.pos.y && point.y <= rect.pos.y + rect.size.y;
	}
	
	public boolean containsPoint(Vec2 point)
	{
		return point.x >= pos.x && point.x <= pos.x + size.x && point.y >= pos.y && point.y <= pos.y + size.y;
	}
	
	public String toString()
	{
		return "Pos: " + pos + ", Size: " + size;
	}
	
	public Rect update(Rect other)
	{
		pos.update(other.pos);
		size.update(other.size);
		
		return this;
	}
}
