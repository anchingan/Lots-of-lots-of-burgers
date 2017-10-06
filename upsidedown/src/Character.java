import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Character{

	private enum state{
		ALIVE,
		DEAD
	}
	Image character;
	Image characterSmile;
	Image characterExplode;
	Image characterNotSmile;
	ArrayList<Image> flyImg;
	private state state;
	private int width, height;
	private final int widthIni, heightIni;
	private int x;
	private final int yDwnVelocityInitial;
	private int yDwnVelocity;
	private int yDwnIncrement;
	private int y;
	private int yAccel;
	private int propTime; //new
	private int coinEaten;
	private int yJumpStart;
	private Boolean smile;
	
	public Character() {
		character = new ImageIcon(getClass().getResource("burger.png")).getImage();
		characterExplode = new ImageIcon(getClass().getResource("explode.png")).getImage();
		characterSmile = new ImageIcon(getClass().getResource("burgerJump4.png")).getImage();
		characterNotSmile = new ImageIcon(getClass().getResource("burger.gif")).getImage();
		character = characterNotSmile; 
		state = state.ALIVE;
		width = widthIni= 100;
		height = heightIni = 80;
		
		x = 160;
		y = 320;
		yJumpStart = y;
		yDwnVelocityInitial = 3;
		yDwnVelocity = yDwnVelocityInitial;
		yDwnIncrement = 1;
		yAccel = -100;
		coinEaten = 0;
		propTime = 0; //new
	}
	
	public void explode() {
		state = state.DEAD;
	}
	
	public void move() {
		y += yDwnVelocity;
		yDwnVelocity += yDwnIncrement;
		if(y > yJumpStart - 100)
			notSmile();
	}
	
	public void eatCoin() {
		coinEaten++;
	}
	
	public void smile() {
		character = characterSmile;
	}
	
	public void notSmile() {
		character = characterNotSmile;
	}
	
	public int getCoinEaten() {
		return coinEaten;
	}
	
	public void accel() {
		yJumpStart = y;
		y += yAccel;
		yDwnVelocity = yDwnVelocityInitial;
		smile();
	}
	
	public void paint (Graphics g) {
		if(state == state.ALIVE)
			g.drawImage(character, x, y, width, height, null);
		else if(state == state.DEAD)
			g.drawImage(characterExplode, x, y, width, height, null);
	}
	
	public int getTopY() {
		return y;
	}
	
	public int getBottomY() {
		return y + height;
	}
	
	public int getLeftX() {
		return x;
	}
	
	public int getRightX() {
		return x + width;
	}
	
	//new
	public int getWidth() {
		return this.width;
	}
	//new
	public int getHeight() {
		return this.height;
	}
	//new
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	//new 
	public void reSize() {
		this.width = this.widthIni;
		this.height = this.heightIni;
	}
	
	public void meetProp(Prop.Type t) {
		
		if (t == Prop.Type.MINUS || t == Prop.Type.PLUS)
			this.propTime = 1;
//			this.setSize(130,  104);
	}
	
	public void propTimePlus() {
		if (this.propTime != 0) {
			this.propTime++;
		}
		if (this.propTime >= 120) {
			this.reSize();
			this.propTime = 0;
		}
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void randomMove() {
		int dirX = (int)(Math.random() * 2);
		int vx = (int)(Math.random() * 7) + 10;
		if(dirX > 0) {
			x += vx;
		}
		else{
			x -= vx;
		}
		y --;
	}
	
	public Boolean isOutofBound() {
		if(y + height > 720 || y < 0)
			return true;
		return false;
	}
	
}
