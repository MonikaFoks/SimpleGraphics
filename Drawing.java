package KO£O2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Drawing extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private int x = 50, y = 100;
	private int r = 15;
	private Color color = Color.ORANGE;
	JButton timerBtn = new JButton("Restart");
	public JTextField timerText = new JTextField(12);
	public JTextField lineCount = new JTextField(15);
	public JTextField circleCount = new JTextField(15);
	public int time = -1;
	public int numberOfLines = 0;
	public int numberOfCirc = 0;
	public int firstX = 0, firstY = 0, secondX = 0, secondY = 0;
	
	private List<Line> lines;
	private List<Circle> circles; 
	
	public Drawing(){
		lines = new LinkedList<Line>();
		circles = new LinkedList<Circle>();
		createTimer();
		createCounters();
		
		addButton();
	}
	
	public void deleteLines(){
		for(Line line : getLines())
			lines.remove(line);
		numberOfLines = 0;
		lineCount.setText("Number of lines: "+numberOfLines);
	}
	
	public void deleteCircles(){
		for(Circle circle : getCircles())
			circles.remove(circle);
		numberOfCirc = 0;
		circleCount.setText("Number of circles: "+numberOfCirc);
	}
	
	private void createTimer(){
		add(timerText);
		timerText.setEditable(false);
	}
	
	private void createCounters(){
		add(lineCount);
		add(circleCount);
		lineCount.setEditable(false);
		circleCount.setEditable(false);
		lineCount.setText("Number of lines: "+numberOfLines);
		circleCount.setText("Number of circles: "+numberOfCirc);
	}
	
	private void addButton(){
		add(timerBtn);
		timerBtn.addActionListener(this);
	}
	
	public Line[] getLines(){
		Line[] array = new Line[0];
		return lines.toArray(array);
	}
	
	public void addLine(Line line){
		lines.add(line);
		numberOfLines+=1;
		lineCount.setText("Number of lines: "+numberOfLines);
	}
	
	public Circle[] getCircles(){
		Circle[] array = new Circle[0];
		return circles.toArray(array);
	}
	
	public void addCircle(Circle circle){
		circles.add(circle);
		numberOfCirc+=1;
		circleCount.setText("Number of circles: "+numberOfCirc);
	}
	
	private void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		
		for(Line line : lines)
			line.draw(g);
		
		for(Circle circle : circles)
			circle.draw(g);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	
	public void moveCircle(){
		if(x < 400)
			x += 1;
		else
			x = 0;
	}
	
	public void moveDrawing(int x, int y){
		for(Circle circle : circles)
			circle.move(x,y);
		for(Line line : lines)
			line.move(x,y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == timerBtn){
			time = -1;
			deleteLines();
			deleteCircles();
			firstX = 0;
			firstY = 0;
		}
	}
}
