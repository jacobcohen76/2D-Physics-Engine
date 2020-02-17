package physicsengine.environment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import physicsengine.gui.Display;
import physicsengine.objects.Collisions;
import physicsengine.objects.Object;
import physicsengine.objects.shapes.Ball;
import physicsengine.objects.shapes.Wall;
import physicsengine.util.cartesian2d.Point;

public class Space implements Runnable
{
	private volatile LinkedList<Ball> balls;
	private volatile LinkedList<Wall> walls; 
	private volatile Display display;
	private volatile long tickspeed;
	
	public Space(long tickspeed, Display display)
	{
		this.balls = new LinkedList<Ball>();
		this.walls = new LinkedList<Wall>();
		this.display = display;
		this.tickspeed = tickspeed;
	}
	
	public boolean add(Ball toAdd)
	{
		return ballsToAdd.add(toAdd);
	}
	
	public boolean add(Wall toAdd)
	{
		return wallsToAdd.add(toAdd);
	}
	
	private void tick(double time)
	{
		for(Ball ball : balls)
			ball.tick(time);
	}
	
	private Queue<Ball> ballsToAdd = new LinkedList<Ball>();
	private Queue<Wall> wallsToAdd = new LinkedList<Wall>();
	
	public void run()
	{
		try {
			double seconds = (double)tickspeed / 1000.0;
			Queue<Object> toRender = new LinkedList<Object>();
			while(true)
			{
				balls.addAll(ballsToAdd);
				walls.addAll(wallsToAdd);
				ballsToAdd.clear();
				wallsToAdd.clear();
				
				toRender.addAll(balls);
				toRender.addAll(walls);
				display.render(toRender);
				toRender.clear();
				Thread.sleep(tickspeed);
				tick(seconds);
				computeCollisions();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		balls.clear();
		walls.clear();
	}
	
	private void computeCollisions()
	{
		Iterator<Ball> ballitr = balls.iterator();
		while(ballitr.hasNext())
		{
			Ball ball = ballitr.next();
			Iterator<Wall> wallitr = walls.iterator();
			while(wallitr.hasNext())
			{
				Wall wall = wallitr.next();
				if(wall.distanceFrom(ball) <= ball.radius)
					Collisions.collide(ball, wall);
			}
			
			Iterator<Ball> ballitr2 = balls.iterator();
			while(ballitr2.hasNext())
			{
				Ball ball2 = ballitr2.next();
				if(ball != ball2 && (Point.distance(ball.pos, ball2.pos) <= ball.radius + ball2.radius))
					Collisions.collide(ball, ball2);
			}
		}
	}
	
	private void runInfiniteLoop() throws InterruptedException
	{
		
	}
}
