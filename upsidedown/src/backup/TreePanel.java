import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TreePanel extends JPanel{

	Image tree;
	public TreePanel(Image tree) {
		this.tree = tree;
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(tree, 0, 0, 25, 25,this);
	}
}
