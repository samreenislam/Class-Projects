import javax.swing.JFrame;

public class DisplayFrame {
	JFrame newF = new JFrame("Tic Tac Nine");
	private Display2 bigD = new Display2(newF);
	public DisplayFrame(){
		//should anything occur here?
	}
	public void run(){
		newF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newF.getContentPane().add(bigD);
		newF.pack();
		newF.setVisible(true);
	}
	public Display2 getBigD(){
		return bigD;
	}
}

