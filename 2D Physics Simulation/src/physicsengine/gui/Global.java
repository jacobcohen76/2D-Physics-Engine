package physicsengine.gui;

public class Global
{
	public static int WIDTH = 0;
	public static int HEIGHT = 300;
	
	public static int getX(int x)
	{
		return x - WIDTH;
	}
	
	public static int getY(int y)
	{
		return HEIGHT - y;
	}
}
