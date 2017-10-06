import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Skyscraper {
	protected int sizeYInitial;
	protected Image skyScrapper;
	protected int x,y;
	protected int sizeX,sizeY;
	protected Boolean enlargeable;
	protected Boolean runnable;
	protected Boolean baseMove;
	protected int enlargeMax;
	protected int enlargeDir;
	
	public Skyscraper(int x, int y,int sizeX, int sizeY, Image img) {
		
		baseMove = false;
		this.runnable = false;
		skyScrapper = img;
		this.enlargeMax = 0;
		this.enlargeable = false;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeYInitial = sizeY;
		enlargeDir = 0;
	}
	
	public void setBaseMove(Boolean b) {
		baseMove = b;
	}
	
	public void setEnlargeable(Boolean b) {
		enlargeable = b;
	}
	
	public Boolean getEnlargeable() {
		return enlargeable;
	}
	
	public void setRunnable(Boolean b) {
		runnable = b;
	}
		
 	public void setEnlargeMax(int max) {
		enlargeMax = max;
	}
	
 	public void enlarge() {
		
		if(enlargeDir == 0) {
			 if(sizeY < enlargeMax) {
				 sizeY ++;
				 if(baseMove)
					 y --;
			 }
			 else {
				 enlargeDir = 1;
			 }
		}
		
		if(enlargeDir == 1) {
			if(sizeY > sizeYInitial - 10) {
				sizeY--;
				if(baseMove)
					y++;
			}				
			else {
				enlargeDir = 0;
			}
		}
	}
 	
	public void move() {
		if(!runnable)
			x --;
		else {
			if(x < 500)
				y --;
			x --;
		}
	}
	
	public void setImage(Image img) {
		this.skyScrapper = img;
	}
	
	public void moveDown() {
		y ++;
	}
	
	public Boolean outOfScene() {
		if(x + sizeX < 0)
			return true;
		return false;
	}
	
	public void paint(Graphics g) {
		g.drawImage(skyScrapper, x,y,sizeX,sizeY,null);
	}
	
	public boolean checkCollide(int xRight, int xLeft, int yTop, int yBottom) {
		int left = this.x;
		int right = this.x + sizeX;
		int top = this.y;
		int bottom = this.y + sizeY;			
		
		if(xRight <= left)
			return false;
		if(xLeft >= right)
			return false;
		if(yTop >= bottom)
			return false;
		if(yBottom <= top)
			return false;
		return true;
	}

	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.sizeX;
	}
	
	public int getHeight() {
		return this.sizeY;
	}

}
