package physicsengine.environment;

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
	
	public synchronized boolean add(Ball toAdd)
	{
		return ballsToAdd.add(toAdd);
	}
	
	public synchronized boolean add(Wall toAdd)
	{
		return wallsToAdd.add(toAdd);
	}
	
	private synchronized void tick(double time)
	{
		for(Ball ball : balls)
			ball.tick(time);
	}
	
	private volatile Queue<Ball> ballsToAdd = new LinkedList<Ball>();
	private volatile Queue<Wall> wallsToAdd = new LinkedList<Wall>();
	private volatile Queue<Object> toRender = new LinkedList<Object>();
	
	private synchronized void updateObjects()
	{
		balls.addAll(ballsToAdd);
		walls.addAll(wallsToAdd);
		toRender.addAll(ballsToAdd);
		toRender.addAll(wallsToAdd);
		ballsToAdd.clear();
		wallsToAdd.clear();
	}
	
	public void run()
	{
		try {
			double seconds = (double)tickspeed / 1000.0;
			while(true)
			{
				updateObjects();
				display.render(toRender);
				Thread.sleep(tickspeed);
				computeCollisions();
				tick(seconds);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void clear()
	{
		balls.clear();
		walls.clear();
		toRender.clear();
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
}
