package KO£O2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Editor extends JFrame implements ActionListener, MouseListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static final String AUTHOR = "Monika Foks, nr indeksu 236664";
	public static final String DESCRIPTION = "Simple graphic program for programming classes.";
	private Drawing drawing = new Drawing();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("MENU");
	private JMenuItem author = new JMenuItem("Author");
	private JMenuItem description = new JMenuItem("Description");
	private JMenuItem exit = new JMenuItem("Exit");
	
	private boolean isShiftDown;
	
	public Editor(){
		super(AUTHOR);
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(drawing);
		createMenu();
		setVisible(true);
		
		addMouseListener(this);
		addKeyListener(this);
	}
	
	private void createMenu(){
		add(menuBar);
		menuBar.add(menu);
		menu.add(author);
		menu.add(description);
		menu.add(exit);
		setJMenuBar(menuBar);
		
		author.addActionListener(this);
		description.addActionListener(this);
		exit.addActionListener(this);
	}
	
	public void runThread(){
		Thread thread = new Thread(){
			@Override
			public void run(){
				while(true){
					drawing.moveCircle();
					repaint();
					try{
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	public void runTimer(){
		Thread timeThread = new Thread(){
			@Override
			public void run(){
				while(true){
					drawing.time++;
					drawing.timerText.setText("Time on the board: "+drawing.time);
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		timeThread.start();
	}
	
	public static void main(String[] args){
		Editor editor = new Editor();
		editor.runThread();
		editor.runTimer();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == author)
			JOptionPane.showMessageDialog(this, AUTHOR);
		if(source == description)
			JOptionPane.showMessageDialog(this, DESCRIPTION);
		if(source == exit)
			System.exit(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		requestFocus();
		if(e.getButton() == 1){
			if((drawing.firstX == 0 && drawing.firstY == 0)||(e.getButton() == 1 && isShiftDown == true)){
				drawing.firstX = e.getX()-3;
				drawing.firstY = e.getY()-50;
				isShiftDown = false;
			} 
			else {
				drawing.secondX = e.getX()-3;
				drawing.secondY = e.getY()-50;
				
				drawing.addLine(new Line(drawing.firstX, drawing.firstY, drawing.secondX, drawing.secondY));
				
				drawing.firstX = drawing.secondX;
				drawing.firstY = drawing.secondY;
			}
		}
		if(e.getButton() == 3){
			drawing.addCircle(new Circle(e.getX(), e.getY()-50));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.isShiftDown()==true)
			isShiftDown = true;
		if(event.getKeyChar() == 'd'||event.getKeyChar() =='D') {
			drawing.deleteLines();
			drawing.deleteCircles();
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT){
			drawing.moveDrawing(1,0);
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT){
			drawing.moveDrawing(-1, 0);
		}
		if(event.getKeyCode() == KeyEvent.VK_UP){
			drawing.moveDrawing(0, -1);
		}
		if(event.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.moveDrawing(0, 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
