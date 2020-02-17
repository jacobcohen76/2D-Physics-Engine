package physicsengine.objects.shapes;

import java.awt.Color;
import java.awt.Graphics;

import physicsengine.gui.Global;
import physicsengine.objects.Object;
import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public class Ball extends Object
{	
	public Color color;
	public double radius;
	
	public Ball(Point pos, Vector velocity, Vector netforce, double mass, double radius, Color color)
	{
		super(pos, velocity, netforce, mass);
		this.radius = radius;
		this.color = color;
	}
	
	public void render(Graphics g)
	{
		g.setColor(color);
		int x = Global.getX((int) (pos.x - radius));
		int y = Global.getY((int) (pos.y + radius));
		int diameter = (int) (radius * 2);
		g.fillOval(x, y, diameter, diameter);
//		velocity.render(g, pos);
	}
}
