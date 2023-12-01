package core.math;

public class Rect
{
	public float x;
	public float y;
	public float width;
	public float height;
	
	public Rect(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public static boolean ContainsPoint(Rect rect, Vec2 point)
	{
		return point.x >= rect.x && point.x <= rect.x + rect.width && point.y >= rect.y && point.y <= rect.y + rect.height;
	}
	
	public boolean containsPoint(Vec2 point)
	{
		return point.x >= x && point.x <= x + width && point.y >= y && point.y <= y + height;
	}
	
	public Rect update(Rect other)
	{
		x = other.x;
		y = other.y;
		width = other.width;
		height = other.height;
		
		return this;
	}
	
	public Rect update(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		return this;
	}
	
	public String toString()
	{
		return "X: " + x + ", Y: " + y + ", Width: " + width + ", Height: " + height;
	}
}
