import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog{
	private int opt;
	private JFrame frame;
	private int mType;
    private int oType;
    private String[] options;
	
	public Dialog(JFrame frame){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	    this.mType = JOptionPane.QUESTION_MESSAGE;
	    this.oType = JOptionPane.YES_NO_OPTION;
	    this.options = new String[] {"繼續遊戲","結束遊戲"};
	    this.frame = frame;
	}
	
	public void show() {
	    opt = JOptionPane.showOptionDialog(frame,"您要繼續遊戲嗎?", "請選擇", oType, mType, null, options, "繼續");
	    if (opt == JOptionPane.YES_OPTION){
	    		GameStart.state = GameStart.STATE.ALIVE;
	      }
	    else 
	    		System.exit(0);
	}
}

