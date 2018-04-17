import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.util.Random;

public class Display2 extends JPanel implements ActionListener {
	
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, currentButton, restartGame, exitGame, restartAll, quit, tieB;
	private JLabel instruct;
	private int p = 1;
	private int[][] board = new int[3][3];
	private int turn = 1;
	private boolean guess;
	private int winner = 0;
	private int win = 0;
	public Display smallDisplay;
	private JFrame smallFrame;
	private JFrame endFrame = new JFrame("");
	private JFrame mainFrame;
	private JFrame tieFrame;
	private JTextField inputText;

	public Display2(JFrame F) {
		mainFrame = F;
		
		b1 = new JButton("");
		b2 = new JButton("");
		b3 = new JButton("");
		b4 = new JButton("");
		b5 = new JButton("");
		b6 = new JButton("");
		b7 = new JButton("");
		b8 = new JButton("");
		b9 = new JButton("");
		restartAll = new JButton("Restart Game");
		quit = new JButton("Quit Game");

		instruct = new JLabel("Tic Tac Nine");

		setPreferredSize(new Dimension(700, 700));
		setLayout(null);

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		add(b8);
		add(b9);
		add(restartAll);
		add(quit);
		add(instruct);

		b1.setBounds(50, 50, 200, 200);
		b2.setBounds(250, 50, 200, 200);
		b3.setBounds(450, 50, 200, 200);
		b4.setBounds(50, 250, 200, 200);
		b5.setBounds(250, 250, 200, 200);
		b6.setBounds(450, 250, 200, 200);
		b7.setBounds(50, 450, 200, 200);
		b8.setBounds(250, 450, 200, 200);
		b9.setBounds(450, 450, 200, 200);
		restartAll.setBounds(50, 5, 200, 40);
		quit.setBounds(450, 5, 200, 40);
		instruct.setBounds(300, 15, 200, 15);
		
		restartAll.setBackground(Color.blue);
		quit.setBackground(Color.yellow);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		restartAll.addActionListener(this);
		quit.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b1;
			newFrame(b1);
		}
		else if(e.getSource()==b2){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b2;
			newFrame(b2);
			//			pressed(b2);
		}
		else if(e.getSource()==b3){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b3;
			newFrame(b3);
			//			pressed(b3);
		}
		else if(e.getSource()==b4){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b4;
			newFrame(b4);
			//			pressed(b4);
		}
		else if(e.getSource()==b5){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b5;
			newFrame(b5);
			//			pressed(b5);
		}
		else if(e.getSource()==b6){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b6;
			newFrame(b6);
			//			pressed(b6);
		}
		else if(e.getSource()==b7){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b7;
			newFrame(b7);
			//			pressed(b7);
		}
		else if(e.getSource()==b8){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b8;
			newFrame(b8);
			//			pressed(b8);
		}
		else if(e.getSource()==b9){
			if (p == 1){
				p = 2;
			} else {
				p = 1;
			}
			currentButton = b9;
			newFrame(b9);
			//			pressed(b9);
		}
		else if (e.getSource() == restartGame) {
			endFrame.dispose();
			mainFrame.dispose();
			DisplayFrame game = new DisplayFrame();
			game.run();
	    }
		else if (e.getSource() == exitGame) {
			System.exit(0);
		}
		else if (e.getSource() == restartAll){
			mainFrame.dispose();
			DisplayFrame game = new DisplayFrame();
			game.run();
		}
		else if (e.getSource() == quit) {
			System.exit(0);
		}
		else if (e.getSource() == tieB) {
			System.out.println("in tieB");
			String input = inputText.getText();
			int number = Integer.parseInt(input);
			Random randy = new Random();
			int randomNum = randy.nextInt(10) + 1;
				if (number == randomNum) {
				guess = true;
				win = p;
				endSmallTie(guess);
				setButtonColor(currentButton, win);
				pressed(currentButton);
			} else {
				guess = false;
				if (p == 1){
					win = 2;
				} else {
					win = 1;
				}
				endSmallTie(guess);
				setButtonColor(currentButton, win);
				pressed(currentButton);
			}
			tieFrame.dispose();
		}
	}
	
	public void pressed(JButton b){
		b.setEnabled(false);
		System.out.println("In pressed!");
		int i = 0;
		int j = 0;
		if(currentButton == b1){
			i=0;
			j=0;
		}else if (currentButton == b2){
			i = 0;
			j = 1;
		}else if (currentButton == b3){
			i = 0;
			j = 2;
		}else if (currentButton == b4){
			i = 1;
			j = 0;
		}else if (currentButton == b5){
			i = 1;
			j = 1;
		}else if (currentButton == b6){
			i = 1;
			j = 2;
		}else if (currentButton == b7){
			i = 2;
			j = 0;
		}else if (currentButton == b8){
			i = 2;
			j = 1;
		}else if (currentButton == b9){
			i = 2;
			j = 2;
		}
		if (win == 1){
			board[i][j] = win;
			System.out.println(win);
			win = 0;
		}
		if (win == 2){
			board[i][j] = win*5;
			System.out.println(win);
			win = 0;
		}
		if (checkArray()){
			System.out.println("Game Over!");
			endGame();
		}
		else if(turn == 9){
			endGame();
		}
		turn++;
	}
	public void smallTie(){
		if (p == 1){
			p = 2;
		} else {
			p = 1;
		}
		tieFrame = new JFrame("Tie Breaker");
		tieFrame.setPreferredSize(new Dimension(350, 150));
		tieFrame.getContentPane().setLayout(null);
		
		JLabel pick = new JLabel("Player " + p + " pick a number from 1 to 10");
		inputText = new JTextField();
		tieB = new JButton("Done!");
		
		tieFrame.getContentPane().add(pick);
		tieFrame.getContentPane().add(inputText);
		tieFrame.getContentPane().add(tieB);
		
		pick.setBounds(25, 25, 250, 15);
		inputText.setBounds(25, 55, 50, 25);
		tieB.setBounds(25, 85, 100, 25);
		
		tieFrame.pack();
		tieFrame.setLocationRelativeTo(null);
		tieFrame.setVisible(true);
	
		tieB.addActionListener(this);
		
		System.out.println("Its a tie!");
	}
	public boolean checkArray(){
		int d1, d2, c1, c2, c3, r1, r2, r3;
		d1 = board[0][0] + board[1][1] + board[2][2];
		d2 = board[0][2] + board[1][1] + board[2][0];
		c1 = board[0][0] + board[0][1] + board[0][2];
		c2 = board[1][0] + board[1][1] + board[1][2];
		c3 = board[2][0] + board[2][1] + board[2][2];
		r1 = board[0][0] + board[1][0] + board[2][0];
		r2 = board[0][1] + board[1][1] + board[2][1];
		r3 = board[0][2] + board[1][2] + board[2][2];
		if(d1 == 3||d2 == 3||c1 == 3||c2 == 3||c3 == 3||r1 == 3||r2 == 3||r3 == 3){
			winner = 1;
			System.out.println("Player 1 the whole game");
			return true;
		}
		else if(d1 == 30||d2 == 30||c1 == 30||c2 == 30||c3 == 30||r1 == 30||r2 == 30||r3 == 30){
			winner = 2;
			System.out.println("Player 2 the whole game");
			return true;
		}
		return false;
	}
	public void finishagame(int winner){
		System.out.println("set winner to " + winner);
		win = winner;
		setButtonColor(currentButton, win);
		endSmallGame();
		smallFrame.dispose();
		if (win == 3){
			smallTie();
		} else {
		pressed(currentButton);
		}
	}
	public void checkWin(int win){
		this.win = win;
	}
	private void newFrame(JButton b){
		JFrame smallF = new JFrame("Tic Tac Toe Mini");
		smallFrame = smallF;
		smallDisplay = new Display(this);
		smallF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		smallF.getContentPane().add(smallDisplay);
		smallF.pack();
		smallF.setVisible(true);
	}
	public void setButtonColor(JButton currentButton, int win) {
		if (win == 1){
			currentButton.setBackground(Color.green);
			currentButton.setContentAreaFilled(false);
			currentButton.setOpaque(true);
		}
		else if (win == 2) {
			currentButton.setBackground(Color.red);
			currentButton.setContentAreaFilled(false);
			currentButton.setOpaque(true);
		}
		else {
			currentButton.setBackground(Color.orange);
			currentButton.setContentAreaFilled(false);
			currentButton.setOpaque(true);
		}
	}
	public void endGame() {
		JFrame frame = new JFrame("");
		restartGame = new JButton("Restart");
		exitGame = new JButton("Exit");
		restartGame.setBounds(25, 25, 150, 75);
		restartGame.setBackground(Color.BLUE);
		exitGame.setBounds(175, 25, 150, 75);
		exitGame.setBackground(Color.YELLOW);
		endFrame.setPreferredSize(new Dimension(350, 150));
		endFrame.getContentPane().setLayout(null);
		endFrame.getContentPane().add(restartGame);
		endFrame.getContentPane().add(exitGame);
		restartGame.addActionListener(this);
		exitGame.addActionListener(this);

		if (winner == 1){
			JOptionPane.showMessageDialog(frame, "Game Over! Player 1 wins!");
			endFrame.pack();
			endFrame.setLocationRelativeTo(null);
			endFrame.setVisible(true);
			//		   System.exit(0);
		} else if (winner == 2){
			JOptionPane.showMessageDialog(frame, "Game Over! Player 2 wins!");
			endFrame.pack();
			endFrame.setLocationRelativeTo(null);
			endFrame.setVisible(true);
			//		   System.exit(0);
		} else {
			JOptionPane.showMessageDialog(frame, "Game Over! It's a tie!");
			endFrame.pack();
			endFrame.setLocationRelativeTo(null);
			endFrame.setVisible(true);
		}
	}
	public void endSmallGame() {
		JFrame frame = new JFrame();
		if (win == 1){
			JOptionPane.showMessageDialog(frame, "Player 1 wins!");
		} else if (win == 2){
			JOptionPane.showMessageDialog(frame, "Player 2 wins!");
		} else {
			JOptionPane.showMessageDialog(frame, "It's a tie!");
		}
	}
	public void endSmallTie(boolean guess) {
		JFrame frame = new JFrame();
		if (guess == true){
			JOptionPane.showMessageDialog(frame, "Correct! Player " + win + " wins!");
		} else {
			JOptionPane.showMessageDialog(frame, "Incorrect! Player " + win + " wins!");
		}
		
	}
	

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}


}