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
	
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 1000;
	
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
	}
	
	public static Ball getNewBall()
	{
		Point pos = new Point(760, 100);
		Vector velocity = new Vector(900, 300);
		Vector netforce = new Vector(0, 0);
		double mass = 10.0;
		double radius = 30.0;
		Color color = randomColor();
		return new Ball(pos, velocity, netforce, mass, radius, color);
	}
	
	private static final Vector GRAVITY = new Vector(0, -10000.0);
	public static void addGravity(Ball ball)
	{
		ball.addForce(Vector.mult(ball.mass, GRAVITY));
	}
	
	public Space space;
	
	private static Random rand = new Random(System.currentTimeMillis());
	
	public static Color randomColor()
	{
		return new Color(rand.nextInt(16777216));
	}
	
	public void init()
	{
		
//		Ball ball = getNewBall();
//		addGravity(ball);
//		ball.mass = 40;
//		ball.radius = 28.0;
//		ball.pos.x = 500;
//		ball.pos.y = 100;
////		space.add(ball);
//		
//		
//		Ball lead = getNewBall();
//		lead.radius = 15;
//		lead.color = Color.GRAY;
//		lead.pos.x = 840;
//		lead.pos.y = 73;
//		lead.mass = 100;
//		lead.velocity = new Vector(0, 0);
//		addGravity(lead);
////		space.add(lead);
//		
//		Ball ball2 = getNewBall();
//		ball2.color = Color.GREEN;
//		ball2.mass = 20.0;
//		ball2.radius = 50;
//		ball2.mass = 100;
//		addGravity(ball2);
//		
//		space.add(ball2);
//		
//		int north = 200;
//		int east = 1500;
//		int south = -500;
//		int west = 300;
//		
//		Wall NORTH = new Wall(new LineSegment(new Point(west, north), new Point(east, north)), Color.RED);
//		Wall EAST = new Wall(new LineSegment(new Point(east, north), new Point(east, south)), Color.RED);
//		Wall SOUTH = new Wall(new LineSegment(new Point(west, south), new Point(east, south)), Color.RED);
//		Wall WEST = new Wall(new LineSegment(new Point(west, south), new Point(west, north)), Color.RED);
//		
//		space.add(NORTH);
//		space.add(EAST);
//		space.add(SOUTH);
//		space.add(WEST);
//		
//		Wall small = new Wall(new LineSegment(new Point(340, -200), new Point(380, -200)), Color.GREEN);
//		space.add(small);		
//		
//		Wall DIAG = new Wall(new LineSegment(new Point(west, south), new Point(east - 200, north - 200)), Color.RED);
//		space.add(DIAG);
//		
//		Wall DIAG2 = new Wall(new LineSegment(new Point(west, north - 400), new Point(east - 200, south + 100)), Color.CYAN);
//		space.add(DIAG2);
//		
//		Wall DIAG3 = new Wall(new LineSegment(new Point(west + 300, north - 400), new Point(east - 200, south + 100)), Color.CYAN);
//		space.add(DIAG3);
		
		this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		GUI visuals = new GUI();
		visuals.init();
		visuals.space.run();
	}
	
	private Point convertPoint(java.awt.Point p)
	{
		return new Point(p.x + Global.WIDTH, Global.HEIGHT - p.y);
	}
	
	private Point temp;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
//		System.out.println("yo" + ball.pos);
//		System.out.println(ball);
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("(" + Global.WIDTH + e.getPoint().x) + ", " + Global.getY(e.getPoint().y) + ")");
		
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
