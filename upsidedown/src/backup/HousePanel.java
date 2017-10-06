import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class HousePanel extends JPanel{
	
	Image house;
	
	public HousePanel(Image house) {
		this.house = house;		
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);				
		g.drawImage(house, 0,0,40,40,this);				
	}
	
}
