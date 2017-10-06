import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SkyscraperList {
	
	private enum size{
		LARGEUP,
		LARGEDOWN,
		LARGEFLY,
		MEDIUMUP,
		MEDIUMDOWN,
		SMALLUP,
		SMALLDOWN,
		MINIUP,
		MINIDOWN,
		MEDIUMUPMOVE,
		MEDIUMDOWNMOVE,
		SPRAYCOKELARGE,
		SPRAYCOKEMINI
		
	}
	private final ArrayList<Skyscraper> skyscraperList;
	private final int largeSspY,largeSspDwnY;
	private final int medSspY,medSspDwnY;
	private final int smlSspY,smlSspDwnY;
	private final int miniSspY, miniSspDwnY;
	private final int width;
	private final int lHeight,mHeight,sHeight,miniHeight;
	private int skyscraperX;
	Image skyscraper;
	Image skyscraperd;
	Image skyscraperFly;
	Image spray;
	Image sprayd;

	
	
	public SkyscraperList() {
		skyscraper = new ImageIcon(getClass().getResource("cokeLightd.png")).getImage();
		skyscraperd = new ImageIcon(getClass().getResource("cokeLight.png")).getImage();
		skyscraperFly = new ImageIcon(getClass().getResource("cokeFly.png")).getImage();
		spray = new ImageIcon(getClass().getResource("spray.png")).getImage();
		sprayd = new ImageIcon(getClass().getResource("sprayd.png")).getImage();
		skyscraperList = new ArrayList<Skyscraper>();
		lHeight = 250;
		mHeight = 190;
		sHeight = 160;
		miniHeight = 100;
		largeSspY = 440;
		largeSspDwnY = 0;
		medSspY = largeSspY + (lHeight - mHeight);
		medSspDwnY = 0;
		smlSspY = largeSspY + (lHeight - sHeight);
		smlSspDwnY = 0;
		miniSspY = largeSspY + (lHeight - miniHeight);
		miniSspDwnY = 0;
		skyscraperX = 400;
		width = 230;
		
				
		
		
		setStage1();
		setStage2();
		setStage3();
		setStage4();
		setStage5();
		
	}
	
	
	private void setStage1() {
		
		
		
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
		skyscraperX += 400;
		
		for(int i = 0; i < 4; i ++) {
			Skyscraper sky;
			
			if(i == 2) 
				this.addSsp(size.SPRAYCOKELARGE);
			else if( i % 2 == 0) {
				this.addSsp(size.LARGEUP);
			}
			else 
				this.addSsp(size.LARGEDOWN);;			
						
			skyscraperX += 400;
		}
	}

	private void setStage2() {
		
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		skyscraperX += 300;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.MEDIUMDOWN);
		skyscraperX += 300;
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
		
		skyscraperX += width;
	}
	
	private void setStage3() {
		
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
			
		skyscraperX += 400;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.LARGEDOWN);
						
		skyscraperX += 400;
		
		for(int i = 0 ; i < 3; i++) {
			this.addSsp(size.MEDIUMDOWN);
			this.addSsp(size.LARGEUP);			
			skyscraperX += 150;
		}
		
		skyscraperX += 250;
		
		this.addSsp(size.SMALLUP);
		this.addSsp(size.MEDIUMDOWNMOVE);
		
		skyscraperX += 250;
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
	}
	
	public void setStage4() {
		skyscraperX += 250;
		
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.LARGEUP);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
		
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 250;
		
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MINIUP);
		
		skyscraperX += 250;
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 250;		
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
	}
	
	public void setStage5() {
		skyscraperX = 7500;
		
		for(int i = 0; i < 5; i ++) {
			this.addSsp(size.LARGEFLY);
			skyscraperX += 300;
		}
		
	}

	private void addSsp(size s) {		
		
		if(s == s.LARGEUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraper));
		else if(s == s.LARGEDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspDwnY,width,lHeight,skyscraperd));
		else if(s == s.MEDIUMDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraper));
		else if(s == s.MEDIUMUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraperd));
		else if(s == s.SMALLUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspY,width,sHeight,skyscraper));
		else if(s == s.SMALLDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspDwnY,width,sHeight,skyscraperd));
		else if(s == s.MINIDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspDwnY,width,miniHeight,skyscraperd));
		else if(s == s.MINIUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspY,width,miniHeight,skyscraper));
		else if(s == s.MEDIUMDOWNMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraperd);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(mHeight + 100);
			skyscraperList.add(temp);
		}
		else if(s == s.MEDIUMUPMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraper);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(mHeight + 100);
			temp.setBaseMove(true);
			skyscraperList.add(temp);
		}
		else if(s == s.SPRAYCOKELARGE) {
			Skyscraper temp  = new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraper);
			skyscraperList.add(new Spray(skyscraperX + 128, largeSspY + 20, 0, 0, spray));
			skyscraperList.add(temp);
			
		}
		else if(s == s.SPRAYCOKEMINI) {
			Skyscraper temp  = new Skyscraper(skyscraperX, miniSspY,width,miniHeight,skyscraper);
			skyscraperList.add(new Spray(skyscraperX + 128, miniSspY + 20, 0, 0, spray));
			skyscraperList.add(temp);					
		}
		else if(s == s.LARGEFLY) {
			Skyscraper temp = new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraperFly);
			temp.setRunnable(true);
			skyscraperList.add(temp);
		}
		
	
	}
	
	
	public void enlargeMovingScp() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).getEnlargeable()) {
				skyscraperList.get(i).enlarge();
			}
		}
	}

	public void moveAll() {
		for(int i = 0; i < skyscraperList.size(); i ++) {			
			skyscraperList.get(i).move();
		}				
		
	}
	
	public void removeOutofScene() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).outOfScene()) {
				skyscraperList.remove(i);
			}
		}
	}

	public Boolean checkCollideAll(Character character) {
		
		Boolean collide = false;
		
		for(int i = 0; i < skyscraperList.size(); i++) {
			 
			collide = skyscraperList.get(i).checkCollide(character.getRightX()
					, character.getLeftX(), character.getTopY(), character.getBottomY());
			
			if(collide)
				return collide;
		}
		
		return collide;
	}

	public void paintAll(Graphics g) {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			skyscraperList.get(i).paint(g);
		}
	}
	
	public Boolean isEmpty() {
		if(skyscraperList.size() < 1) {
			return true;
		}
		return false;
	}
}

/*
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SkyscraperList {
	
	private enum size{
		LARGEUP,
		LARGEDOWN,
		LARGEFLY,
		MEDIUMUP,
		MEDIUMDOWN,
		SMALLUP,
		SMALLDOWN,
		MINIUP,
		MINIDOWN,
		MEDIUMUPMOVE,
		MEDIUMDOWNMOVE,
		SPRAYCOKELARGE,
		SPRAYCOKEMINI
		
	}
	private final ArrayList<Skyscraper> skyscraperList;
	private final int largeSspY,largeSspDwnY;
	private final int medSspY,medSspDwnY;
	private final int smlSspY,smlSspDwnY;
	private final int miniSspY, miniSspDwnY;
	private final int width;
	private final int lHeight,mHeight,sHeight,miniHeight;
	private int skyscraperX;
	Image skyscraper;
	Image skyscraperd;
	Image skyscraperFly;
	Image spray;
	Image sprayd;
	
	public SkyscraperList() {
		skyscraper = new ImageIcon(getClass().getResource("cokeLightd.png")).getImage();
		skyscraperd = new ImageIcon(getClass().getResource("cokeLight.png")).getImage();
		skyscraperFly = new ImageIcon(getClass().getResource("cokeFly.png")).getImage();
		spray = new ImageIcon(getClass().getResource("spray.png")).getImage();
		sprayd = new ImageIcon(getClass().getResource("sprayd.png")).getImage();
		skyscraperList = new ArrayList<Skyscraper>();
		lHeight = 250;
		mHeight = 190;
		sHeight = 160;
		miniHeight = 100;
		largeSspY = 440;
		largeSspDwnY = 0;
		medSspY = largeSspY + (lHeight - mHeight);
		medSspDwnY = 0;
		smlSspY = largeSspY + (lHeight - sHeight);
		smlSspDwnY = 0;
		miniSspY = largeSspY + (lHeight - miniHeight);
		miniSspDwnY = 0;
		skyscraperX = 400;
		width = 270;
		
		setStage1();
		setStage2();
		setStage3();
		setStage4();
		setStage5();
		
	}
	
	private void setStage1() {
		
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
		skyscraperX += 400;
		
		for(int i = 0; i < 4; i ++) {
			Skyscraper sky;
			
			if(i == 2) 
				this.addSsp(size.SPRAYCOKELARGE);
			else if( i % 2 == 0) {
				this.addSsp(size.LARGEUP);
			}
			else 
				this.addSsp(size.LARGEDOWN);;			
						
			skyscraperX += 400;
		}
	}

	private void setStage2() {
		
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		skyscraperX += 300;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.MEDIUMDOWN);
		skyscraperX += 300;
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
		
		skyscraperX += width;
	}
	
	private void setStage3() {
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
			
		skyscraperX += 400;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.LARGEDOWN);
						
		skyscraperX += 400;
		
		for(int i = 0 ; i < 3; i++) {
			this.addSsp(size.MEDIUMDOWN);
			this.addSsp(size.LARGEUP);			
			skyscraperX += 150;
		}
		
		skyscraperX += 250;
		
		this.addSsp(size.SMALLUP);
		this.addSsp(size.MEDIUMDOWNMOVE);
		
		skyscraperX += 250;
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
	}
	
	public void setStage4() {
		skyscraperX += 250;
		
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.LARGEUP);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 250;
		
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MINIUP);
		
		skyscraperX += 250;
		this.addSsp(size.MINIDOWN);
		this.addSsp(size.SPRAYCOKEMINI);
		
		skyscraperX += 250;
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 250;		
		this.addSsp(size.MEDIUMDOWNMOVE);
		this.addSsp(size.MEDIUMUPMOVE);
		
		System.out.println(skyscraperX);
		
	}
	
	public void setStage5() {
		skyscraperX = 6000;
		for(int i = 0; i < 5; i ++) {
			this.addSsp(size.LARGEFLY);
			skyscraperX += 300;
		}
	}

	private void addSsp(size s) {		
		
		if(s == s.LARGEUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraper));
		else if(s == s.LARGEDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspDwnY,width,lHeight,skyscraperd));
		else if(s == s.MEDIUMDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraper));
		else if(s == s.MEDIUMUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraperd));
		else if(s == s.SMALLUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspY,width,sHeight,skyscraper));
		else if(s == s.SMALLDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspDwnY,width,sHeight,skyscraperd));
		else if(s == s.MINIDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspDwnY,width,miniHeight,skyscraperd));
		else if(s == s.MINIUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspY,width,miniHeight,skyscraper));
		else if(s == s.MEDIUMDOWNMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraperd);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(mHeight + 100);
			skyscraperList.add(temp);
		}
		else if(s == s.MEDIUMUPMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraper);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(mHeight + 100);
			temp.setBaseMove(true);
			skyscraperList.add(temp);
		}
		else if(s == s.SPRAYCOKELARGE) {
			Skyscraper temp  = new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraper);
			skyscraperList.add(new Spray(skyscraperX + 128, largeSspY + 20, 0, 0, spray));
			skyscraperList.add(temp);
			
		}
		else if(s == s.SPRAYCOKEMINI) {
			Skyscraper temp  = new Skyscraper(skyscraperX, miniSspY,width,miniHeight,skyscraper);
			skyscraperList.add(new Spray(skyscraperX + 128, miniSspY + 20, 0, 0, spray));
			skyscraperList.add(temp);					
		}
		else if(s == s.LARGEFLY) {
			Skyscraper temp = new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraperFly);
			temp.setRunnable(true);
			skyscraperList.add(temp);
		}
	}
	
	public void enlargeMovingScp() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).getEnlargeable()) {
				skyscraperList.get(i).enlarge();
			}
		}
	}

	public void moveAll() {
		for(int i = 0; i < skyscraperList.size(); i ++) {			
			skyscraperList.get(i).move();
		}				
	}
	
	public void removeOutofScene() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).outOfScene()) {
				skyscraperList.remove(i);
			}
		}
	}

	public Boolean checkCollideAll(Character character) {
		Boolean collide = false;
		for(int i = 0; i < skyscraperList.size(); i++) {
			collide = skyscraperList.get(i).checkCollide(character.getRightX()
					, character.getLeftX(), character.getTopY(), character.getBottomY());
			if(collide)
				return collide;
		}
		return collide;
	}

	public void paintAll(Graphics g) {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			skyscraperList.get(i).paint(g);
		}
	}
	
	public Boolean isEmpty() {
		if(skyscraperList.size() < 1) {
			return true;
		}
		return false;
	}
}
*/

/*
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class SkyscraperList {
	
	private enum size{
		LARGEUP,
		LARGEDOWN,
		MEDIUMUP,
		MEDIUMDOWN,
		SMALLUP,
		SMALLDOWN,
		MINIUP,
		MINIDOWN,
		MEDIUMUPMOVE,
		MEDIUMDOWNMOVE,
		COIN
	}
	private final ArrayList<Skyscraper> skyscraperList;
	private final int largeSspY,largeSspDwnY;
	private final int medSspY,medSspDwnY;
	private final int smlSspY,smlSspDwnY;
	private final int miniSspY, miniSspDwnY;
	private final int width;
	private final int lHeight,mHeight,sHeight,miniHeight;
	private int skyscraperX;
	private int checkIndex, screenEnd; //new
	Image skyscraper;
	Image skyscraperd;
	Image coin;
	Image spray;
	private Prop donut, chili;
	
	
	public SkyscraperList() {
//		skyscraper = new ImageIcon(getClass().getResource("cokeLight.png")).getImage();
//		skyscraperd = new ImageIcon(getClass().getResource("cokeLightd.png")).getImage();
		skyscraper = new ImageIcon(getClass().getResource("coke1pruple.png")).getImage();
		skyscraperd = new ImageIcon(getClass().getResource("coke1prupled.png")).getImage();
		coin = new ImageIcon(getClass().getResource("coin.png")).getImage();
		spray = new ImageIcon(getClass().getResource("spray.png")).getImage();
		skyscraperList = new ArrayList<Skyscraper>();
		lHeight = 300;
		mHeight = 180;
		sHeight = 120;
		miniHeight = 80;
		largeSspY = 390;
		largeSspDwnY = 0;
		medSspY = largeSspY + (lHeight - mHeight);
		medSspDwnY = 0;
		smlSspY = largeSspY + (lHeight - sHeight);
		smlSspDwnY = 0;
		miniSspY = largeSspY + (lHeight - miniHeight);
		miniSspDwnY = 0;
		skyscraperX = 400;
		width = 150;
		screenEnd = 15; //new
		
		setStage1();
		setStage2();
		setStage3();
		
		
	}
	
	
	private void setStage1() {
		
		donut = new Prop(Prop.Type.PLUS, 300, 200, 50);
		chili = new Prop(Prop.Type.MINUS, 1200, 400, 60);
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
		
		skyscraperX += 300;
		
		for(int i = 0; i < 4; i ++) {
			Skyscraper sky;
			
			if(i % 2 == 0) {
//				this.addSpray(skyscraperX - 10, largeSspY - 100);
				this.addSsp(size.LARGEUP);
			}
			else 
				this.addSsp(size.LARGEDOWN);;
				
						
			skyscraperX += 300;
		}
		
	}

	private void setStage2() {
		
		this.addSsp(size.MINIDOWN);
		this.addSpray(skyscraperX - 10, largeSspY - 100);
		this.addSsp(size.MINIUP);
		skyscraperX += 300;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.MEDIUMDOWN);
		skyscraperX += 300;
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
		
		skyscraperX += width;
	}
	
	private void setStage3() {
		
		
		this.addSsp(size.LARGEUP);
		this.addSsp(size.MEDIUMDOWN);
			
		skyscraperX += 300;
		
		this.addSsp(size.MEDIUMUP);
		this.addSsp(size.LARGEDOWN);
						
		skyscraperX += 300;
		
		for(int i = 0 ; i < 3; i++) {
			this.addSsp(size.MEDIUMDOWN);
			this.addSsp(size.LARGEUP);			
			skyscraperX += 150;
		}
		
		skyscraperX += 150;
		
		this.addSsp(size.SMALLUP);
		this.addSsp(size.MEDIUMDOWNMOVE);
		
		skyscraperX += 150;
		this.addSsp(size.SMALLDOWN);
		this.addSsp(size.MEDIUMUPMOVE);
	}

	private void addSsp(size s) {		
		
		if(s == s.LARGEUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspY,width,lHeight,skyscraper));
		else if(s == s.LARGEDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, largeSspDwnY,width,lHeight,skyscraperd));
		else if(s == s.MEDIUMDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraper));
		else if(s == s.MEDIUMUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraperd));
		else if(s == s.SMALLUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspY,width,sHeight,skyscraper));
		else if(s == s.SMALLDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, smlSspDwnY,width,sHeight,skyscraperd));
		else if(s == s.MINIDOWN)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspDwnY,width,miniHeight,skyscraperd));
		else if(s == s.MINIUP)
			this.skyscraperList.add(new Skyscraper(skyscraperX, miniSspY,width,miniHeight,skyscraper));
		else if(s == s.MEDIUMDOWNMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspDwnY,width,mHeight,skyscraperd);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(miniHeight + 250);
			skyscraperList.add(temp);
		}
		else if(s == s.MEDIUMUPMOVE) {
			Skyscraper temp = new Skyscraper(skyscraperX, medSspY,width,mHeight,skyscraper);
			temp.setEnlargeable(true);
			temp.setEnlargeMax(miniHeight + 250);
			temp.setBaseMove(true);
			skyscraperList.add(temp);
		}
	
	}
	
	private void addCoin(int x, int y) {
		skyscraperList.add(new Skyscraper(x, y ,50,50,coin));
	}

	public void enlargeMovingScp() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).getEnlargeable()) {
				skyscraperList.get(i).enlarge();
			}
		}
	}

	public void moveAll() {
		for(int i = 0; i < skyscraperList.size(); i ++) {			
			skyscraperList.get(i).move();
		}				
		if (donut != null)
			donut.move();
		if (chili != null)
			chili.move();
	}
	
	public void removeOutofScene() {
		for(int i = 0; i < skyscraperList.size(); i ++) {
			if(skyscraperList.get(i).outOfScene()) {
				skyscraperList.remove(i);
			}
		}
	}

	public Boolean checkCollideAll(Character character) {
		
		Boolean collide = false;
		
		for(int i = 0; i < skyscraperList.size(); i++) {
			 
			collide = skyscraperList.get(i).checkCollide(character.getRightX()
					, character.getLeftX(), character.getTopY(), character.getBottomY());
			
			if(collide) 
				return collide;
			
			int xCheck = skyscraperList.get(i).getX();
			if (xCheck > 1250) {
				screenEnd = i;
				break;
			}
		}
		if (donut != null && donut.checkCollide(character))
			donut = null;
		if (chili != null && chili.checkCollide(character))
			chili = null;
		
		return collide;
	}
	
	public void paintAll(Graphics g) {
		if (donut != null)
			donut.paint(g);
		if (chili != null)
			chili.paint(g);
		for(int i = 0; i <= screenEnd && i < skyscraperList.size(); i ++) {
			skyscraperList.get(i).paint(g);
		}
	}
	
	public void addSpray(int x, int y) {
		skyscraperList.add(new Spray(x, y ,300,200,spray));
	}
}
*/