import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Countdown extends JPanel {
	
	private ArrayList<Image> images;
	public int index;

	public Countdown() {
		images = new ArrayList<Image>();
		images.add(new ImageIcon(getClass().getResource("number-3.png")).getImage());
		images.add(new ImageIcon(getClass().getResource("number-2.png")).getImage());
		images.add(new ImageIcon(getClass().getResource("number-1.png")).getImage());
		images.add(new ImageIcon(getClass().getResource("start2.png")).getImage());
		images.add(new ImageIcon(getClass().getResource("start2.png")).getImage());
		index = 0;
	}
	
	public void next() {
		if (this.index < 4)
			this.index++;
	}
	
	@Override
	public void paint(Graphics g) {
		if (index >= 3)
			g.drawImage(images.get(index), 400, 200, 400, 200, null);
		else
			g.drawImage(images.get(index), 500, 200, 200, 200, null);
	}
	
}
