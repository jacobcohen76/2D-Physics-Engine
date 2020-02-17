package physicsengine.objects.shapes;

import java.awt.Color;
import java.awt.Graphics;

import physicsengine.gui.Global;
import physicsengine.objects.ImmovableObject;
import physicsengine.objects.Object;
import physicsengine.util.cartesian2d.LineSegment;
import physicsengine.util.cartesian2d.Point;
import physicsengine.util.cartesian2d.Vector;

public class Wall extends ImmovableObject
{
	public LineSegment segment;
	public Color color;	
	
	public Wall(LineSegment segment, Color color)
	{
		super(segment.getMidPoint());
		this.segment = segment;		
		this.color = color;
	}

	public void render(Graphics g)
	{
		g.setColor(color);
		int x1 = Global.getX((int) segment.a.x);
		int y1 = Global.getY((int) segment.a.y);
		int x2 = Global.getX((int) segment.b.x);
		int y2 = Global.getY((int) segment.b.y);
		g.drawLine(x1, y1, x2, y2);
//		segment.render(g);
	}
	
	public Vector getShortestLineTo(Point pos)
	{
		return new Vector(segment.getPointClosestTo(pos), pos);
	}
	
	public double distanceFrom(Object object)
	{
		return segment.distanceFrom(object.pos);
	}
	
	public Vector getNormal()
	{
		return segment.getNormal();
	}
}
