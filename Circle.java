package KO£O2;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {

	int x, y;
	int r = 10;
	private Color color = Color.BLUE;
	
	public Circle(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(int x, int y){
		this.x+=x;
		this.y+=y;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}
}
