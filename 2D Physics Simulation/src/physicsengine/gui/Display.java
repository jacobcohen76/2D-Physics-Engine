package physicsengine.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

import physicsengine.objects.Object;

public class Display extends JPanel
{
	private static final long serialVersionUID = 387636754339140630L;
	
	private Queue<Object> renderQueue;
	
	public Display()
	{
		this.setBackground(Color.BLACK);
		renderQueue = new LinkedList<Object>();
	}
	
	public void render(Collection<Object> toRender)
	{
		renderQueue.addAll(toRender);
		repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		while(renderQueue.isEmpty() == false)
			renderQueue.poll().render(g);
	}
}
