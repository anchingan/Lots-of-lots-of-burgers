import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

public class GameStart {
	public static enum STATE{
		FAIL,
		ALIVE,
		OPTION,
		COUNTDOWN,
		SUCCEED
	}	
	public static STATE state;
	private static Timer checkGameStateTimer;
	private static GamePanel gp;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		try {
			gp = new GamePanel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gp.setSize(1200, 720);
		frame.add(gp);
		GameScene gc = new GameScene(5000, 720);
		frame.add(gc);
		frame.setLayout(null);				
		frame.setSize(1200, 720);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		state = STATE.OPTION;
		checkGameStateTimer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(state == STATE.FAIL) {
					gp.restart();
				}
			}
		});
		checkGameStateTimer.start();
	}

}

