import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Expt extends JPanel implements ActionListener {

	Timer time = new Timer(10, this);
	int x = 0;
	int xVel = 2;
	int y = 0;
	int yVel = 2;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getxVel() {
		return xVel;
	}
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getyVel() {
		return yVel;
	}
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	
	public Expt() {
	
    }
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.orange);
		g.fillOval(x, y, 50, 50);
//		g.drawString("Awesome", x+1, y+1);
		
		time.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(x < 0 || x > 650 || y < 0 || y > 650) {
			xVel = - xVel;
			yVel = - yVel;
		}
		x = x + xVel;
		y = y + yVel;
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Expt one = new Expt();
		Expt two = new Expt();
		two.setX(600);
		two.setY(600);
		Expt three = new Expt();
		three.setX(300);
		three.setY(200);
		Expt four = new Expt();
		four.setxVel(4);
		Expt five = new Expt();
		five.setyVel(4);
		f.setSize(700,700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.add(one);
		f.add(two);
		f.add(three);
		f.add(four);
		f.add(five);
		

	}

}
