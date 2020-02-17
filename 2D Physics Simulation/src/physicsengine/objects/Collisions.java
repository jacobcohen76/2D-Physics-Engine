package physicsengine.objects;

import physicsengine.objects.shapes.Ball;
import physicsengine.objects.shapes.Wall;
import physicsengine.util.cartesian2d.Vector;

public class Collisions
{
	private static Vector getNewVelocity(Ball ball1, Ball ball2)
	{
		double mCoeff = 2 * ball2.mass / (ball1.mass + ball2.mass);
		Vector vDiff = Vector.sub(ball1.velocity, ball2.velocity);
		Vector xDiff = new Vector(ball1.pos, ball2.pos);
		Vector projection = Vector.proj(vDiff, xDiff);
		return Vector.sub(ball1.velocity, Vector.mult(mCoeff, projection));
	}
	
	public static void collide(Ball ball1, Ball ball2)
	{
		Vector v1 = getNewVelocity(ball1, ball2);
		Vector v2 = getNewVelocity(ball2, ball1);		
		ball1.velocity = v1;
		ball2.velocity = v2;
	}
	
	public static void collide(Ball ball, Wall wall)
	{
		Vector proj = Vector.proj(wall.getShortestLineTo(ball.pos), ball.velocity);
		ball.velocity = Vector.sub(ball.velocity, Vector.mult(2.0, proj));
	}
}
