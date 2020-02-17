package physicsengine.objects;

import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public abstract class ImmovableObject extends Object
{
	public ImmovableObject(Point pos)
	{
		super(pos);
	}
	
	public void addForce(Vector forceComponent)
	{
		
	}
	
	public void removeForce(Vector forceComponent)
	{
		
	}
	
	public void tick(double time)
	{
		
	}
	
	public abstract double distanceFrom(Object object);
}
