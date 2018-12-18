package KO�O2;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
	
	int x1, x2, y1, y2;
	
	public Line(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void move(int x, int y){
		this.x1+=x;
		this.x2+=x;
		this.y1+=y;
		this.y2+=y;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
	}
}
