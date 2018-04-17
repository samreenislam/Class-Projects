import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class Display extends JPanel implements ActionListener {
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JLabel instruct;
	private int[][] board = new int[3][3];
	private int turn = 1;
	private int winner = 0;
    private Display2 father;
    private int p = 1;
	public Display(Display2 father) {
		
		b1 = new JButton("");
		b2 = new JButton("");
		b3 = new JButton("");
		b4 = new JButton("");
		b5 = new JButton("");
		b6 = new JButton("");
		b7 = new JButton("");
		b8 = new JButton("");
		b9 = new JButton("");
		
		this.father=father;
		instruct = new JLabel("Tic Tac Toe");

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
		instruct.setBounds(300, 15, 200, 15);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		
		p = father.getP();
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			if (p == 1){
				board[0][0] = p;
			}
			if (p == 2){
				board[0][0] = p*5;
			}
			pressed(b1);
		}
		else if(e.getSource()==b2){
			if (p == 1){
				board[0][1] = p;
			}
			if (p == 2){
				board[0][1] = p*5;
			}
			pressed(b2);
		}
		else if(e.getSource()==b3){
			if (p == 1){
				board[0][2] = p;
			}
			if (p == 2){
				board[0][2] = p*5;
			}
			pressed(b3);
		}
		else if(e.getSource()==b4){
			if (p == 1){
				board[1][0] = p;
			}
			if (p == 2){
				board[1][0] = p*5;
			}
			pressed(b4);
		}
		else if(e.getSource()==b5){
			if (p == 1){
				board[1][1] = p;
			}
			if (p == 2){
				board[1][1] = p*5;
			}
			pressed(b5);
		}
		else if(e.getSource()==b6){
			if (p == 1){
				board[1][2] = p;
			}
			if (p == 2){
				board[1][2] = p*5;
			}
			pressed(b6);
		}
		else if(e.getSource()==b7){
			if (p == 1){
				board[2][0] = p;
			}
			if (p == 2){
				board[2][0] = p*5;
			}
			pressed(b7);
		}
		else if(e.getSource()==b8){
			if (p == 1){
				board[2][1] = p;
			}
			if (p == 2){
				board[2][1] = p*5;
			}
			pressed(b8);
		}
		else if(e.getSource()==b9){
			if (p == 1){
				board[2][2] = p;
			}
			if (p == 2){
				board[2][2] = p*5;
			}
			pressed(b9);
		}
	}
	public void pressed(JButton b){
		b.setEnabled(false);
		if (turn <= 9){
			if(p == 1){
				b.setBackground(Color.green);
				b.setContentAreaFilled(false);
				b.setOpaque(true);
				p = 2;
			}
			else{
				b.setBackground(Color.red);
				b.setContentAreaFilled(false);
				b.setOpaque(true);
				p = 1;
			}
			if (checkArray()){
				
			}
			else if(turn == 9){
				father.finishagame(3);
			}
			turn++;
		}
	}
//	public void tie(){
//		System.out.println("Its a tie");
//	}
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
				setWinner(1); //how to use?
				System.out.println("Player 1 wins");
				father.finishagame(1);
				return true;
			}
			else if(d1 == 30||d2 == 30||c1 == 30||c2 == 30||c3 == 30||r1 == 30||r2 == 30||r3 == 30){
				setWinner(2);
				System.out.println("Player 2 wins");
				father.finishagame(2);
				return true;
				
			} 
				return false;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	
}