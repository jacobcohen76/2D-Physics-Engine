package physicsengine.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;

import physicsengine.environment.Space;
import physicsengine.objects.shapes.Ball;
import physicsengine.objects.shapes.Wall;
import physicsengine.util.cartesian2d.LineSegment;
import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public class GUI extends JFrame implements MouseListener
{
	private static final long serialVersionUID = -6317281367173512812L;
	private static final Vector GRAVITY = new Vector(0, -10000.0);
	private static final Random rand = new Random(System.currentTimeMillis());
	
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 1000;
	
	public Space space;
	
	private Display display;
	
	public GUI()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocationRelativeTo(null);
		
		display = new Display();
		this.add(display);
		space = new Space(1L, display);
		this.getContentPane().addMouseListener(this);
		this.setVisible(true);
	}
	
	public static Ball getNewBall()
	{
		Point pos = new Point(760, 100);
		Vector velocity = new Vector(0, 0);
		Vector netforce = new Vector(0, 0);
		double mass = 10.0;
		double radius = 30.0;
		Color color = randomColor();
		return new Ball(pos, velocity, netforce, mass, radius, color);
	}
	
	public static void addGravity(Ball ball)
	{
		ball.addForce(Vector.mult(ball.mass, GRAVITY));
	}
	
	public static Color randomColor()
	{
		return new Color(rand.nextInt(16777216));
	}
	
	public void run()
	{
		Thread spaceThread = new Thread(space);
		spaceThread.setPriority(Thread.MAX_PRIORITY);
		spaceThread.start();
	}
	
	public static void main(String args[])
	{
		GUI visuals = new GUI();
		visuals.run();
	}
	
	private Point convertPoint(java.awt.Point p)
	{
		return new Point(p.x + Global.WIDTH, Global.HEIGHT - p.y);
	}
	
	//used to store previous click location
	private Point temp;
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			Ball ball = getNewBall();
			ball.pos = convertPoint(e.getPoint());
			addGravity(ball);
			space.add(ball);
		}
		else if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(temp == null)
				temp = convertPoint(e.getPoint());
			else
			{
				space.add(new Wall(new LineSegment(temp, convertPoint(e.getPoint())), randomColor()));
				temp = null;
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON2)
		{
			space.clear();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
