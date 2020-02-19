package physicsengine.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import physicsengine.objects.Object;

public class Display extends JPanel
{
	private static final long serialVersionUID = 387636754339140630L;
	
	private volatile Collection<Object> renderQueue;
	
	public Display()
	{
		this.setBackground(Color.BLACK);
		renderQueue = new LinkedList<Object>();
	}
	
	public synchronized void render(Collection<Object> toRender)
	{
		renderQueue.clear();
		renderQueue.addAll(toRender);
		repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Iterator<Object> itr = renderQueue.iterator();
		while(itr.hasNext())
			itr.next().render(g);
	}
}
