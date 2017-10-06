import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScene extends JPanel{

	private final int width;
	private final int height;
	private int x;
	private int y;
	private ArrayList<Image> propsUp;
	private ArrayList<Image> propsDown;
	private ArrayList<Image> propsUpTree;
	private ArrayList<Image> propsDownTree;
	private ArrayList<Image> bgs;//new
	private Image cloudUp;
	private Image cloudDown;
	private Image bg; //new
	private Image bg2;
	
	public GameScene(int w, int h)
	{
		width = 120000;//w * 100; //100����
		height = h;
		bgs = new ArrayList<Image>();
		loadImage();			
		
		this.setSize(width, height);
		this.setLayout(null);
		this.setBackground(new Color(214, 214, 194));
		setBg();//new
		
	}
	
	private void setBg() {
		int x = 0, y = 0;
		while (x < width) {
			BackGroundPanel bgp = new BackGroundPanel(bg);			
			
			this.add(bgp);
			bgp.setLocation(x, y);
			bgp.setBackground(null);
			bgp.setOpaque(false);
			bgp.setSize(1200,720);
			
			x += 1200;
		}
		
	}

	private void loadImage() {
		bg = new ImageIcon(getClass().getResource("background.gif")).getImage();//new
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);	
	}
	
}
