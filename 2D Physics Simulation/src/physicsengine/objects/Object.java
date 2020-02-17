package physicsengine.objects;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public abstract class Object
{
	public Point pos;
	public Vector velocity;
	public Vector netforce;
	public double mass;
	
	private Vector acceleration;
	private Set<Vector> forceComponents;
	
	private Object(Point pos, Vector velocity, Vector acceleration, Vector netforce, double mass, Set<Vector> forceComponents)
	{
		this.pos = pos;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.netforce = netforce;
		this.mass = mass;
		this.forceComponents = forceComponents;
	}
	
	public Object(Point pos, Vector velocity, Vector netforce, double mass)
	{
		this(pos, velocity, new Vector(0, 0), netforce, mass, new HashSet<Vector>());
	}
	
	public Object(Point pos)
	{
		this(pos, new Vector(0, 0), new Vector(0, 0), 0.0);
	}
	
	public Object()
	{
		this(new Point(0, 0), new Vector(0, 0), new Vector(0, 0), 0.0);
	}
	
	/**
	 * Updates the acceleration of this Object based on the current netforce Vector
	 * of this Object according to the formula credited to Isaac Newton, F = ma.
	 */
	private void updateAcceleration()
	{
		acceleration = Vector.div(netforce, mass);
	}
	
	public void addForce(Vector forceComponent)
	{
		netforce = Vector.add(netforce, forceComponent);
		forceComponents.add(forceComponent);
		updateAcceleration();
	}
	
	public void removeForce(Vector forceComponent)
	{
		if(forceComponents.remove(forceComponent) == true)
		{
			netforce = Vector.sub(netforce, forceComponent);
			updateAcceleration();
		}
	}
	
	/**
	 * Updates the position of this Object as would be expected by using the
	 * Kinematics Equations. Specifically the equation Pf = Pi + Vi * t + A * t * t / 2.
	 * 
	 * @param time the amount of time that has transpired since this Object
	 * has last had its position updated. The unit for this time interval
	 * should be the same as the unit used for the time component of the
	 * velocity and acceleration Vectors of this Object.
	 */
	private void updatePosition(double time)
	{
		pos = Point.add(pos, Vector.mult(time, velocity));
		pos = Point.add(pos, Vector.mult(time * time / 2, acceleration));
	}
	
	/**
	 * Updates the Velocity of this Object as would be expected by using the
	 * Kinematics Equations. Specifically the equation Vf = Vi + A * t.
	 * 
	 * @param time the amount of time that has transpired since this Object
	 * has last had its velocity updated. The unit for this time interval
	 * should be the same as the unit used for the time component of the
	 * velocity and acceleration Vectors of this Object.
	 */
	private void updateVelocity(double time)
	{
		velocity = Vector.add(velocity, Vector.mult(time, acceleration));
	}
	
	/**
	 * To be used to update the velocity, and position of this object as would be
	 * expected of an Object traveling in linear motion according to what would
	 * be expected if you were to use the Kinematics Equations to compute how an
	 * Object would move.
	 * 
	 * @param time the amount time of time that has passed since this Object has last
	 * been updated. The unit for this time interval should be the same as the unit
	 * used for the time component of the velocity and acceleration Vectors of this
	 * Object.
	 */
	public void tick(double time)
	{
		updatePosition(time);
		updateVelocity(time);
	}
	
	public abstract void render(Graphics g);
	
	public String toString()
	{
		String str = "";
		str += "x = " + pos + "\n";
		str += "v = " + velocity + "\n";
		str += "F = " + netforce + "\n";
		str += "a = " + acceleration + "\n";
		str += "m = " + mass;
		return str;
	}
}
