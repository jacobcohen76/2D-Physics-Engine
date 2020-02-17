import java.awt.Color;

import physicsengine.objects.Collisions;
import physicsengine.objects.shapes.Ball;
import physicsengine.util.cartesian2d.Line;
import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public class Test
{
	public static Ball getNewBall()
	{
		Point pos = new Point(0, 0);
		Vector velocity = new Vector(0, 0);
		Vector netforce = new Vector(0, 0);
		double mass = 10.0;
		double radius = 5.0;
		Color color = Color.BLUE;
		return new Ball(pos, velocity, netforce, mass, radius, color);
	}
	
	public static Vector GRAVITY = new Vector(0, -9.8);
	
	public static Vector getGravity(Ball ball)
	{
		return Vector.mult(ball.mass, GRAVITY);
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		testLineSegments();
//		testProj();
//		testCollisions();
//		Ball ball = getNewBall();
//		ball.addForce(getGravity(ball));
//		
//		double tickspeed = 1000.0;
//		double totalticks = 0;
//		
//		while(true)
//		{
//			System.out.println((totalticks / tickspeed) + "s");
//			System.out.println(ball + "\n");
//			Thread.sleep((long)tickspeed);
//			ball.tick(1000.0 / (double)tickspeed);
//			totalticks += tickspeed;
//		}
	}
	
	public static void testLineSegments()
	{
		Point a = new Point(0, -10);
		Point b = new Point(1, -7);
		Line line = new Line(new Vector(1, -2), new Vector(0, -1));
		System.out.println(line);
		System.out.println(line.distanceFrom(new Point(1, 1)));
	}
	
	public static void testCollisions()
	{
		Ball ball1 = getNewBall();
		Ball ball2 = getNewBall();
		
		ball1.velocity = new Vector(1, 0);
		ball1.pos = new Point(10, 10);
		ball2.velocity = new Vector(0, 1);
		ball2.pos = new Point(20, 20);
		
		Collisions.collide(ball1, ball2);
	}
	
	public static void testProj()
	{
		Vector u = new Vector(10, 3);
		Vector v = new Vector(5, 9);
		
		System.out.println(Vector.proj(u, v));
	}
}
