import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


import javax.swing.*;

public class GamePanel extends JPanel{	
	private int pointCounter;
	private int point;
	private int highestPoint;
	private final GameScene gameScene;
	private final Timer objectMoveTimer;
	private final Timer characterMoveTimer;
	private final Timer countdownTimer; //new
	private	Timer gameWinTimer;
	private	Timer littleCharactersTimer;
	private final AudioClip jump;
	private final AudioClip backGround;
	private final AudioClip fail;
	private final AudioClip clap;
	private final int objectMoveSec;
	private final int characterMoveSec;
	private final int gameWinSec;
	private final Image coke;
	private final Image cokeShake;
	private Skyscraper badCoke;
	private Character character;
	private SkyscraperList skyscraperList;
	private PropList propList;
	private int gameSceneX;	
	private JPanel jp;
	private ArrayList<Image> countdownImages;
	private Image startA, startB, leaveA, leaveB, restartA, restartB, leaveRA, leaveRB;
	private Image bannerP, bannerG;
	private int startX, startY, leaveX, leaveY, width, height;
	private int index; //new
	private boolean start, leave;
	private Boolean bottom;
	private Boolean explode;
	private ArrayList<Character> littleCharacters;
	private FileWriter fw;
	private FileReader fr; 

	
	public GamePanel() throws IOException {	
		
		fr = new FileReader("score.txt");
		BufferedReader br = new BufferedReader(fr);
		point = 0;
		pointCounter = 0;
		highestPoint = Integer.parseInt(br.readLine().trim());
		br.close();
		littleCharacters = new ArrayList<Character>();
		gameScene = new GameScene(1200,720);
		gameSceneX = 0;		
		character = new Character();
		skyscraperList = new SkyscraperList();
		propList = new PropList();
		objectMoveSec = 10;
		characterMoveSec = 50;
		gameWinSec = 10;
		explode = false;
		jump = Applet.newAudioClip(getClass().getResource("jump.wav"));
		backGround = Applet.newAudioClip(getClass().getResource("background.wav"));
		fail = Applet.newAudioClip(getClass().getResource("fail.wav"));
		clap = Applet.newAudioClip(getClass().getResource("cheer.wav"));
		coke = new ImageIcon(getClass().getResource("coke.png")).getImage();
		cokeShake = new ImageIcon(getClass().getResource("cokeShake.gif")).getImage();
		badCoke = new Skyscraper(500, 0, 270, 250,coke);
		this.addMouseListener(new CMouseListener1());
		this.addMouseMotionListener(new CMouseListener1());
		this.setLayout(null);
		this.add(gameScene);
		bottom = false;
		loadCountdown(); //new
		backGround.loop();

		gameWinTimer = new Timer(gameWinSec, new ActionListener() {
			int x = 1;
			public void actionPerformed(ActionEvent evt) {
				if(badCoke.y < 400) {
					badCoke.moveDown();
				}
				else if(!bottom) {
					badCoke.setImage(cokeShake);
					bottom = true;					
				}
				if(bottom && x < 400) {
					x ++;
				}
				else if(x < 500 && bottom){
					badCoke.setImage(new ImageIcon(getClass().getResource("boom.png")).getImage());
					x ++;
				}
				else if(bottom) {
					badCoke.sizeX =0;
					explode = true;
					littleCharactersTimer.start();
					gameWinTimer.stop();
				}
				repaint();
			}
		});
		for(int i = 0; i < 20; i ++) {
			Character temp = new Character();
			temp.setLocation((int)(Math.random() * 1200), (int)(Math.random() * 720));
			littleCharacters.add(temp);
		}
		littleCharactersTimer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				for(int i = 0; i < littleCharacters.size(); i++) {
					littleCharacters.get(i).randomMove();
				}
				repaint();
			}
		});
		countdownTimer = new Timer(800, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GameStart.state == GameStart.STATE.COUNTDOWN) {
					index++;
					repaint();
				}
				if (index > 4) {
					repaint();
					GameStart.state = GameStart.STATE.ALIVE;
					character = new Character();
					skyscraperList = new SkyscraperList();
					propList = new PropList();
					backGround.loop();
					characterMoveTimer.start();
					objectMoveTimer.start();
					countdownTimer.stop();
				}
			}
		});
		
		objectMoveTimer = new Timer(objectMoveSec, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {				
				gameScene.setLocation(gameSceneX--, 0);	
			    skyscraperList.moveAll();	
			    propList.removeAll();
			    skyscraperList.removeOutofScene();
			    skyscraperList.enlargeMovingScp();
			    if(propList.checkCollideAll(character))
			    		pointCounter += 1000;
			    propList.moveAll();
			    if(point > highestPoint) {
			    		highestPoint = point;
		    			String s = highestPoint +"";
			    		try {	    		
			    			fw = new FileWriter("score.txt"); 
			    			fw.write(s);
			    			fw.flush();
			    			fw.close();
			    		}
			    		catch (IOException e) {
						e.printStackTrace();
			    		}
			    }
			    
			    if(skyscraperList.checkCollideAll(character) || character.isOutofBound()) {
				    	character.explode();
				    	backGround.stop();
				    	fail.play();
				    	pointCounter = 0;
				    	point = 0;
				    	GameStart.state = GameStart.STATE.FAIL;
				    	objectMoveTimer.stop();
				    	characterMoveTimer.stop();
				    		
			    }
			    if(skyscraperList.isEmpty()) {
			    		GameStart.state = GameStart.STATE.SUCCEED;
			    		clap.play();	
			    		gameWinTimer.start();
			    		characterMoveTimer.stop();
			    		objectMoveTimer.stop();
			    }
			    pointCounter++;
			    point = (int)pointCounter / 10;

			}	
		});

		characterMoveTimer = new Timer(characterMoveSec, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				character.move();	
				character.propTimePlus();
			}
		});
	}
	
	@Override public void paint(Graphics g) {		
		super.paint(g);	

		//Decide which state is and paint elements of that stage.
		if (GameStart.state == GameStart.STATE.OPTION) {
			g.drawImage(bannerG, 188, 100, 825, 122, null);
			g.drawImage(bannerP, 188, 100, 825, 122, null);

			if (start) {
				g.drawImage(startA, startX, startY, width, height, null);
				
			}
			else {
				g.drawImage(startB, startX, startY, width, height, null);
			}
			if (leave)
				g.drawImage(leaveA, leaveX, leaveY, width, height, null);
			else
				g.drawImage(leaveB, leaveX, leaveY, width, height, null);
		}
		else {
			skyscraperList.paintAll(g);
			character.paint(g);
			propList.paintAll(g);
			Font f = new Font("Comic Sans MS", Font.BOLD, 30);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.drawString("COINS" + " " + character.getCoinEaten(), 1000, 25);
			g.drawString("POINT" + point, 750, 25);
			g.drawString("HIGHESTPOINT" + highestPoint, 350, 25);

			if(explode) {
				for(int i = 0; i < littleCharacters.size(); i ++) {
					littleCharacters.get(i).paint(g);
				}
			}
			else if(GameStart.state == GameStart.STATE.SUCCEED) {
				badCoke.paint(g);
			}
			else if (GameStart.state == GameStart.STATE.FAIL) {
				g.setColor(new Color(255, 255, 255,150));
				g.fillRect(0, 0, 1200, 720);
				if (start) {
					g.drawImage(restartA, startX, startY, width, height, null);
				}
				else {
					g.drawImage(restartB, startX, startY, width, height, null);
				}
				if (leave)
					g.drawImage(leaveRA, leaveX, leaveY, width, height, null);
				else
					g.drawImage(leaveRB, leaveX, leaveY, width, height, null);

			}
			else if (GameStart.state == GameStart.STATE.COUNTDOWN) {
				g.setColor(new Color(255, 255, 255,150));
				g.fillRect(0, 0, 1200, 720);
				if (index >= 3)
					g.drawImage(countdownImages.get(index), 400, 200, 400, 200, null);
				else
					g.drawImage(countdownImages.get(index), 500, 200, 200, 200, null);
			}
		}
	}
	
	class CMouseListener1 extends MouseAdapter{
		@Override 
		public void mousePressed(MouseEvent e) {
			if (GameStart.state == GameStart.STATE.OPTION || GameStart.state == GameStart.STATE.FAIL) {
				int x = e.getX(), y = e.getY();
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (x > startX && x < startX + 300 && y > startY && y < startY + 80) {
						GameStart.state = GameStart.STATE.COUNTDOWN;
						repaint();
						character = new Character();
						skyscraperList = new SkyscraperList();
						start = true;
						countdownTimer.start();
					}
					if (x > leaveX && x < leaveX + 300 && y > leaveY && y < leaveY + 80)
						System.exit(0);
				}
			}
			else if (GameStart.state == GameStart.STATE.ALIVE) {
				if (e.getButton() == MouseEvent.BUTTON1)
					character.accel();
					jump.play();
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (GameStart.state == GameStart.STATE.OPTION || GameStart.state == GameStart.STATE.FAIL) {
				int x = e.getX(), y = e.getY();
				if (x > startX && x < startX + 300 && y > startY && y < startY + 80) {
					start = true;
					repaint();
				}
				else if (x > leaveX && x < leaveX + 300 && y > leaveY && y < leaveY + 80) {
					leave = true;
					repaint();
				}
				else {
					start = false;
					leave = false;
					repaint();
				}
			}
		}
	}
	
	public void restart() {
		index = 0;
		repaint();
		gameSceneX = 0;		
	}
	
	private void loadCountdown() {
		startB = new ImageIcon(getClass().getResource("startB.png")).getImage();
		leaveB = new ImageIcon(getClass().getResource("leaveB.png")).getImage();
		startA = new ImageIcon(getClass().getResource("startA.png")).getImage();
		leaveA = new ImageIcon(getClass().getResource("leaveA.png")).getImage();
		restartB = new ImageIcon(getClass().getResource("restartB.png")).getImage();
		leaveRB = new ImageIcon(getClass().getResource("leaveRB.png")).getImage();
		restartA = new ImageIcon(getClass().getResource("restartA.png")).getImage();
		leaveRA = new ImageIcon(getClass().getResource("leaveRA.png")).getImage();
		bannerP = new ImageIcon(getClass().getResource("banner.png")).getImage();
		bannerG = new ImageIcon(getClass().getResource("banner.gif")).getImage();
		leave = false;
		start = false;
		startX = 220;
		startY = 350;
		leaveX = 650;
		leaveY = 350;
		width = 300;
		height = 80;
		countdownImages = new ArrayList<Image>();
		countdownImages.add(new ImageIcon(getClass().getResource("number-3.png")).getImage());
		countdownImages.add(new ImageIcon(getClass().getResource("number-2.png")).getImage());
		countdownImages.add(new ImageIcon(getClass().getResource("number-1.png")).getImage());
		countdownImages.add(new ImageIcon(getClass().getResource("start2.png")).getImage());
		countdownImages.add(new ImageIcon(getClass().getResource("start2.png")).getImage());
		index = 0;
	}
	
}
