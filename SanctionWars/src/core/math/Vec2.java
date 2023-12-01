package core.math;

import core.graphics.window.Window;

public class Vec2
{
	public float x;
	public float y;
	
	public Vec2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static Vec2 Add(Vec2 lhs, Vec2 rhs)
	{
		return new Vec2(lhs.x + rhs.x, lhs.y + rhs.y);
	}
	
	public static Vec2 Sub(Vec2 lhs, Vec2 rhs)
	{
		return new Vec2(lhs.x - rhs.x, lhs.y - rhs.y);
	}
	
	public static Vec2 Mult(Vec2 lhs, float factor)
	{
		return new Vec2(lhs.x * factor, lhs.y * factor);
	}
	
	public static Vec2 Div(Vec2 lhs, float factor)
	{
		return new Vec2(lhs.x / factor, lhs.y / factor);
	}
	
	public static Vec2 Zero()
	{
		return new Vec2(0, 0);
	}
	
	public static Vec2 One()
	{
		return new Vec2(1, 1);
	}
	
	public static Vec2 ToWorldCoord(Vec2 point)
	{
		float worldX = (point.x / Window.Instance.getScreenWidth()) * Window.RENDER_WIDTH;
		float worldY = (point.y / Window.Instance.getScreenHeight()) * Window.RENDER_HEIGHT;
		
		return new Vec2(worldX, worldY);
	}
	
	public Vec2 copy()
	{
		return new Vec2(x, y);
	}
	
	public Vec2 add(Vec2 other)
	{
		x += other.x;
		y += other.y;
		
		return this;
	}
	
	public Vec2 sub(Vec2 other)
	{
		x -= other.x;
		y -= other.y;
		
		return this;
	}
	
	public Vec2 mult(float factor)
	{
		x *= factor;
		y *= factor;
		
		return this;
	}
	
	public Vec2 div(float factor)
	{
		x /= factor;
		y /= factor;
		
		return this;
	}
	
	public Vec2 update(Vec2 other)
	{
		x = other.x;
		y = other.y;
		
		return this;
	}
	
	public Vec2 update(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	public Vec2 toWorldCoord()
	{
		x = (x / Window.Instance.getScreenWidth()) * Window.RENDER_WIDTH;
		y = (y / Window.Instance.getScreenHeight()) * Window.RENDER_HEIGHT;
		
		return this;
	}
	
	public String toString()
	{
		return "X: " + x + ", Y: " + y;
	}
}
